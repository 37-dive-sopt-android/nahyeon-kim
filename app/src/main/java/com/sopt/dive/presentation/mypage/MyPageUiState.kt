package com.sopt.dive.presentation.mypage

import androidx.compose.runtime.Immutable

@Immutable
data class MyPageUiState(
    val id: Long = 0L,
    val username: String = "",
    val name: String = "",
    val email: String = "",
    val age: Int = 0
)