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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.component.InputItem
import com.sopt.dive.core.component.SoptBasicButton
import com.sopt.dive.core.extension.noRippleClickable
import com.sopt.dive.ui.theme.DiveTheme

@Composable
private fun SignInScreen(
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTextClick: () -> Unit,
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
        Text(text = "Welcome To SOPT", fontSize = 32.sp)

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

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "회원가입하기",
            textDecoration = TextDecoration.Underline,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .noRippleClickable(onClick = onTextClick)
                .padding(bottom = 8.dp)
        )

        SoptBasicButton(
            title = "로그인하기",
            onClick = onButtonClick,
        )
    }
}

@Composable
fun SignInRoute() {

}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    DiveTheme {
        SignInScreen(
            id = "testUser",
            password = "1234",
            onIdChange = {},
            onPasswordChange = {},
            onTextClick = {},
            onButtonClick = {}
        )
    }
}
