package com.sopt.dive.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.util.UiState
import com.sopt.dive.core.util.updateSuccess
import com.sopt.dive.data.UserPreferences
import com.sopt.dive.data.model.SignInRequestModel
import com.sopt.dive.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<SignInUiState>>(
        UiState.Success(SignInUiState())
    )
    val uiState: StateFlow<UiState<SignInUiState>> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect: SharedFlow<SignInSideEffect> = _sideEffect.asSharedFlow()

    fun updateUsername(username: String) {
        _uiState.updateSuccess { it.copy(username = username) }
    }

    fun updatePassword(password: String) {
        _uiState.updateSuccess { it.copy(password = password) }
    }

    fun signIn() {
        val formData = (_uiState.value as? UiState.Success)?.data ?: return

        formData.validationError?.let { error ->
            viewModelScope.launch {
                _sideEffect.emit(SignInSideEffect.ShowToast(error))
            }
            return
        }

        viewModelScope.launch {
            authRepository.postSignIn(
                SignInRequestModel(
                    username = formData.username,
                    password = formData.password
                )
            ).onSuccess { signInModel ->
                userPreferences.setUser(formData.username, formData.password)
                userPreferences.setUserId(signInModel.userId)

                _uiState.updateSuccess {
                    it.copy(userId = signInModel.userId)
                }

                _sideEffect.emit(SignInSideEffect.ShowToast("로그인 성공! ${formData.username}님 환영합니다."))
                _sideEffect.emit(SignInSideEffect.NavigateToHome)
            }.onFailure {
                _sideEffect.emit(SignInSideEffect.ShowToast("로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요."))
            }
        }
    }
}

sealed interface SignInSideEffect {
    data class ShowToast(val message: String) : SignInSideEffect
    data object NavigateToHome : SignInSideEffect
}