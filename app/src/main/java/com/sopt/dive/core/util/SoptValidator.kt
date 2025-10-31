package com.sopt.dive.core.util

import android.content.Context
import android.widget.Toast
import com.sopt.dive.core.data.UserPreferences

fun validateSignUp(
    context: Context,
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
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        false
    } else {
        true
    }
}

fun validateSignIn(
    context: Context,
    idText: String,
    passwordText: String
): Boolean {
    val errorMessage = when {
        idText.isBlank() -> "아이디를 입력해주세요"
        passwordText.isBlank() -> "비밀번호를 입력해주세요"
        else -> null
    }

    return if (errorMessage != null) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        false
    } else {
        true
    }
}

fun handleSignUp(
    context: Context,
    userPrefs: UserPreferences,
    id: String,
    password: String,
    nickname: String,
    mbti: String,
    onSuccess: () -> Unit
) {
    if (!validateSignUp(context, id, password, nickname, mbti)) {
        return
    }

    userPrefs.registerUser(id, password, nickname, mbti)
    Toast.makeText(context, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
    onSuccess()
}

fun handleSignIn(
    context: Context,
    userPrefs: UserPreferences,
    id: String,
    password: String,
    onSuccess: () -> Unit
) {
    if (!validateSignIn(context, id, password)) {
        return
    }

    val loginSuccess = userPrefs.signIn(id, password)

    if (loginSuccess) {
        Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
        onSuccess()
    } else {
        Toast.makeText(context, "아이디 또는 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
    }
}
