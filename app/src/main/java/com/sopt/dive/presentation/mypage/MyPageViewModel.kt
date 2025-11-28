package com.sopt.dive.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.data.repository.UserRepository
import com.sopt.dive.core.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<MyPageUiState>>(UiState.Empty)
    val uiState: StateFlow<UiState<MyPageUiState>> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MyPageSideEffect>()
    val sideEffect: SharedFlow<MyPageSideEffect> = _sideEffect.asSharedFlow()

    init {
        loadUserInfo()
    }

    fun loadUserInfo() {
        val userId = userPreferences.getUserId()
        loadUserInfoById(userId)
    }

    private fun loadUserInfoById(userId: Long) {
        viewModelScope.launch {
            userRepository.getUser(userId)
                .onSuccess { memberModel ->
                    _uiState.update {
                        UiState.Success(
                            MyPageUiState(
                                id = memberModel.id,
                                username = memberModel.username,
                                name = memberModel.name,
                                email = memberModel.email,
                                age = memberModel.age
                            )
                        )
                    }
                }
                .onFailure {
                    _sideEffect.emit(MyPageSideEffect.ShowToast("사용자 정보를 불러오는데 실패했습니다."))
                }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            userPreferences.signOut()
            _sideEffect.emit(MyPageSideEffect.NavigateToSignIn)
        }
    }
}

sealed interface MyPageSideEffect {
    data class ShowToast(val message: String) : MyPageSideEffect
    data object NavigateToSignIn : MyPageSideEffect
}
