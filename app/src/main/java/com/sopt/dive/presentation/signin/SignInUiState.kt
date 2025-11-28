package com.sopt.dive.presentation.signin

import androidx.compose.runtime.Immutable

@Immutable
data class SignInUiState(
    val username: String = "",
    val password: String = "",
    val userId: Long? = null,
    val signInSuccessName: String? = null
) {
    val validationError: String?
        get() = when {
            username.isBlank() -> "아이디를 입력해주세요"
            password.isBlank() -> "비밀번호를 입력해주세요"
            else -> null
        }
}