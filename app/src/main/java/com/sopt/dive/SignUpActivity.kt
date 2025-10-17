package com.sopt.dive

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.component.SoptBasicButton
import com.sopt.dive.core.component.item.InputItem
import com.sopt.dive.core.component.item.TextFieldType
import com.sopt.dive.core.extension.validateSignUp
import com.sopt.dive.data.UserPreferences
import com.sopt.dive.ui.theme.DiveTheme

class SignUpActivity : ComponentActivity() {
    private lateinit var userPrefs: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPrefs = UserPreferences(this)

        setContent {
            DiveTheme {
                SingUpRoute(
                    onSignUpClick = { id, password, nickname, mbti ->
                        userPrefs.registerUser(id, password, nickname, mbti)

                        Toast.makeText(this@SignUpActivity, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
                        setResult(RESULT_OK)
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun SingUpRoute(
    onSignUpClick: (String, String, String, String) -> Unit
) {
    val (id, setId) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (nickname, setNickname) = remember { mutableStateOf("") }
    val (mbti, setMbti) = remember { mutableStateOf("") }
    val context = LocalContext.current

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
            val isValid = validateSignUp(
                context = context,
                idText = id,
                passwordText = password,
                nicknameText = nickname,
                mbtiText = mbti
            )
            if (isValid) {
                onSignUpClick(id, password, nickname, mbti)
            }
        }
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
    val focusRequesterPassword = remember { FocusRequester() }
    val focusRequesterNickname = remember { FocusRequester() }
    val focusRequesterMbti = remember { FocusRequester() }

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
            onNext = { focusRequesterPassword.requestFocus() }
        )

        InputItem(
            label = "Password",
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "비밀번호를 입력해주세요",
            type = TextFieldType.Password,
            onNext = { focusRequesterNickname.requestFocus() },
            modifier = Modifier.focusRequester(focusRequesterPassword)
        )

        InputItem(
            label = "Nickname",
            value = nickname,
            onValueChange = onNicknameChange,
            placeholder = "닉네임을 입력해주세요",
            onNext = { focusRequesterMbti.requestFocus() },
            modifier = Modifier.focusRequester(focusRequesterNickname)
        )

        InputItem(
            label = "MBTI",
            value = mbti,
            onValueChange = onMbtiChange,
            placeholder = "MBTI를 입력해주세요",
            imeAction = ImeAction.Default,
            onNext = onButtonClick,
            modifier = Modifier.focusRequester(focusRequesterMbti)
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
