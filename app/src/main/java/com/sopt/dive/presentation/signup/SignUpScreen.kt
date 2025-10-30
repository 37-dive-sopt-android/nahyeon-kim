package com.sopt.dive.presentation.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.designsystem.component.SoptBasicButton
import com.sopt.dive.core.designsystem.component.item.InputItem
import com.sopt.dive.core.designsystem.component.item.TextFieldType
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.handleSignUp

@Composable
fun SignUpRoute(
    paddingValues: PaddingValues,
    onSignUpSuccess: () -> Unit
) {
    val (id, setId) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (nickname, setNickname) = remember { mutableStateOf("") }
    val (mbti, setMbti) = remember { mutableStateOf("") }
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    SignUpScreen(
        id = id,
        password = password,
        nickname = nickname,
        mbti = mbti,
        onIdChange = setId,
        onPasswordChange = setPassword,
        onNicknameChange = setNickname,
        onMbtiChange = setMbti,
        onButtonClick = {
            handleSignUp(
                context = context,
                userPrefs = userPrefs,
                id = id,
                password = password,
                nickname = nickname,
                mbti = mbti,
                onSuccess = onSignUpSuccess
            )
        },
        modifier = Modifier.padding(paddingValues)
    )
}

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
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA))
            .verticalScroll(rememberScrollState())
            .imePadding()
            .systemBarsPadding()
            .navigationBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(30.dp))

        InputItem(
            label = "ID",
            value = id,
            onValueChange = onIdChange,
            placeholder = "아이디를 입력해주세요",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        InputItem(
            label = "Password",
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "비밀번호를 입력해주세요",
            type = TextFieldType.Password,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
        )

        InputItem(
            label = "Nickname",
            value = nickname,
            onValueChange = onNicknameChange,
            placeholder = "닉네임을 입력해주세요",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
        )

        InputItem(
            label = "MBTI",
            value = mbti,
            onValueChange = onMbtiChange,
            placeholder = "MBTI를 입력해주세요",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
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
