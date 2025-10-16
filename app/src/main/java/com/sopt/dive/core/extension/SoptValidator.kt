package com.sopt.dive.core.extension

import androidx.compose.material3.SnackbarHostState

suspend fun soptValidator(
    snackbarHostState: SnackbarHostState,
    idText: String,
    passwordText: String,
    nicknameText: String,
    mbtiText: String
): Boolean {
    val errorMessage = when {

        idText.isBlank() -> "아이디를 입력해주세요"
        idText.length !in 6..10 -> "아이디는 6~10자리로 입력해주세요"

        passwordText.isBlank() -> "비밀번호를 입력해주세요"
        passwordText.length !in 8..12 -> "비밀번호는 8~12자리로 입력해주세요"

        nicknameText.isBlank() -> "닉네임을 입력해주세요"

        mbtiText.isBlank() -> "MBTI를 입력해주세요"

        else -> null
    }

    return if (errorMessage != null) {
        snackbarHostState.showSnackbar(errorMessage)
        false
    } else {
        true
    }
}
