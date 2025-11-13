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
}