package com.sopt.dive.presentation.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.data.RepositoryProvider
import com.sopt.dive.core.data.repository.UserRepository
import com.sopt.dive.core.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val userRepository: UserRepository = RepositoryProvider.userRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<MyPageUiState>>(UiState.Empty)
    val uiState: StateFlow<UiState<MyPageUiState>> = _uiState.asStateFlow()

    fun loadUserInfo(userId: Long) {

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
                    _uiState.update { UiState.Failure }
                }
        }
    }
}