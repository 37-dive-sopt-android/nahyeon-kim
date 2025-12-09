package com.sopt.dive.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.util.UiState
import com.sopt.dive.core.util.updateSuccess
import com.sopt.dive.data.model.SignUpRequestModel
import com.sopt.dive.data.repository.UserRepository
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
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<SignUpUiState>>(
        UiState.Success(SignUpUiState())
    )
    val uiState: StateFlow<UiState<SignUpUiState>> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect: SharedFlow<SignUpSideEffect> = _sideEffect.asSharedFlow()

    fun updateUsername(username: String) {
        _uiState.updateSuccess { it.copy(username = username) }
    }

    fun updatePassword(password: String) {
        _uiState.updateSuccess { it.copy(password = password) }
    }

    fun updateName(name: String) {
        _uiState.updateSuccess { it.copy(name = name) }
    }

    fun updateEmail(email: String) {
        _uiState.updateSuccess { it.copy(email = email) }
    }

    fun updateAge(age: String) {
        val filtered = age.filter { it.isDigit() }
        _uiState.updateSuccess { it.copy(age = filtered) }
    }

    fun signUp() {
        val formData = (_uiState.value as? UiState.Success)?.data ?: return

        formData.validationError?.let { error ->
            viewModelScope.launch {
                _sideEffect.emit(SignUpSideEffect.ShowToast(error))
            }
            return
        }

        viewModelScope.launch {
            userRepository.postSignUp(
                SignUpRequestModel(
                    username = formData.username,
                    password = formData.password,
                    name = formData.name,
                    email = formData.email,
                    age = formData.ageValue!!
                )
            ).onSuccess { memberModel ->
                _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입 성공! ${memberModel.name}님 환영합니다."))
                _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
            }.onFailure {
                _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입에 실패했습니다."))
            }
        }
    }
}

sealed interface SignUpSideEffect {
    data class ShowToast(val message: String) : SignUpSideEffect
    data object NavigateToSignIn : SignUpSideEffect
}