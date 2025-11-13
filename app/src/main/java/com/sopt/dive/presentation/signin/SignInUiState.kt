package com.sopt.dive.presentation.signin

import androidx.compose.runtime.Immutable

@Immutable
data class SignInUiState(
    val username: String = "",
    val password: String = "",
    val userId: Long? = null,
    val signInSuccessName: String? = null
)