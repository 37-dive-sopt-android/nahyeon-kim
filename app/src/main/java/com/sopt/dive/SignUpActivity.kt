package com.sopt.dive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.component.InputItem
import com.sopt.dive.core.component.SoptBasicButton
import com.sopt.dive.ui.theme.DiveTheme

@Composable
private fun SignUpScreen(
    id: String,
    password: String,
    nickname: String,
    mbti: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onMbtiChange: (String) -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(color = Color(0xFFFAFAFA))
            .padding(16.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(30.dp))

        InputItem(
            label = "ID",
            value = id,
            onValueChange = onIdChange,
            placeholder = "아이디를 입력해주세요"
        )

        InputItem(
            label = "Password",
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "비밀번호를 입력해주세요",
            isPassword = true
        )

        InputItem(
            label = "Nickname",
            value = nickname,
            onValueChange = onNicknameChange,
            placeholder = "닉네임을 입력해주세요"
        )

        InputItem(
            label = "MBTI",
            value = mbti,
            onValueChange = onMbtiChange,
            placeholder = "MBTI를 입력해주세요"

        )

        Spacer(modifier = Modifier.weight(1f))

        SoptBasicButton(
            title = "회원가입하기",
            onClick = onButtonClick
        )
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(
            id = "testUser",
            password = "1234",
            nickname = "테스트",
            mbti = "ISTJ",
            onIdChange = {},
            onPasswordChange = {},
            onNicknameChange = {},
            onMbtiChange = {},
            onButtonClick = {}
        )
    }
}