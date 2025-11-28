package com.sopt.dive.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.data.model.SignUpRequestModel
import com.sopt.dive.core.data.repository.UserRepository
import com.sopt.dive.core.util.UiState
import com.sopt.dive.core.util.updateSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
        if (!formData.isValid) return

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
                _uiState.updateSuccess {
                    it.copy(signUpSuccessName = memberModel.name)
                }
            }.onFailure {
                _uiState.update { UiState.Failure }
            }
        }
    }

    fun resetSignUpState() {
        _uiState.update { UiState.Success(SignUpUiState()) }
    }
}