package com.sopt.dive.presentation.signup

import androidx.compose.runtime.Immutable

@Immutable
data class SignUpUiState(
    val username: String = "",
    val password: String = "",
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val signUpSuccessName: String? = null
) {
    val isValid: Boolean
        get() = username.isNotBlank() &&
                password.isNotBlank() &&
                name.isNotBlank() &&
                email.isNotBlank() &&
                age.toIntOrNull() != null

    val ageValue: Int?
        get() = age.toIntOrNull()

    val validationError: String?
        get() = when {
            username.isBlank() -> "아이디를 입력해주세요"
            username.length !in 6..10 -> "아이디는 6~10자리로 입력해주세요"
            password.isBlank() -> "비밀번호를 입력해주세요"
            password.length !in 8..12 -> "비밀번호는 8~12자리로 입력해주세요"
            name.isBlank() -> "이름을 입력해주세요"
            email.isBlank() -> "이메일을 입력해주세요"
            age.toIntOrNull() == null -> "올바른 나이를 입력해주세요"
            else -> null
        }
}