package com.sopt.dive.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.data.model.SignInRequestModel
import com.sopt.dive.core.data.repository.AuthRepository
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
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<SignInUiState>>(
        UiState.Success(SignInUiState())
    )
    val uiState: StateFlow<UiState<SignInUiState>> = _uiState.asStateFlow()

    fun updateUsername(username: String) {
        _uiState.updateSuccess { it.copy(username = username) }
    }

    fun updatePassword(password: String) {
        _uiState.updateSuccess { it.copy(password = password) }
    }

    fun signIn() {
        val formData = (_uiState.value as? UiState.Success)?.data ?: return
        if (formData.username.isBlank() || formData.password.isBlank()) return

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
                    it.copy(
                        signInSuccessName = it.username,
                        userId = signInModel.userId
                    )
                }
            }.onFailure {
                _uiState.update { UiState.Failure }
            }
        }
    }

    fun resetSignInState() {
        _uiState.update { UiState.Success(SignInUiState()) }
    }
}