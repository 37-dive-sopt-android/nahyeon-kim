package com.sopt.dive.presentation.signin

import android.content.Intent
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.presentation.signup.SignUpActivity
import com.sopt.dive.core.designsystem.component.SoptBasicButton
import com.sopt.dive.core.designsystem.component.item.InputItem
import com.sopt.dive.core.designsystem.component.item.TextFieldType
import com.sopt.dive.core.extension.noRippleClickable
import com.sopt.dive.core.util.validateSignIn
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.presentation.main.MainActivity

class SignInActivity : ComponentActivity() {
    private lateinit var userPrefs: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPrefs = UserPreferences(this)

        if (userPrefs.isSignedIn()) {
            navigateToMain()
            return
        }

        setContent {
            DiveTheme {
                SignInRoute(
                    onSignUpClick = {
                        startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
                    },
                    onSignInClick = { id, password ->
                        handleSignIn(id, password)
                    }
                )
            }
        }
    }

    private fun handleSignIn(id: String, password: String) {
        if (!validateSignIn(this, id, password)) {
            return
        }

        val loginSuccess = userPrefs.signIn(id, password)

        if (loginSuccess) {
            Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
            navigateToMain()
        } else {
            Toast.makeText(this, "아이디 또는 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}

@Composable
fun SignInRoute(
    onSignUpClick: () -> Unit,
    onSignInClick: (String, String) -> Unit
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    SignInScreen(
        id = id,
        password = password,
        onIdChange = { id = it },
        onPasswordChange = { password = it },
        onTextClick = onSignUpClick,
        onButtonClick = { onSignInClick(id, password) }
    )
}

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
    val focusRequesterPassword = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA))
            .padding(16.dp)
            .imePadding()
            .systemBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome To SOPT", fontSize = 32.sp)

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
            imeAction = ImeAction.Done,
            onNext = onButtonClick,
            modifier = Modifier.focusRequester(focusRequesterPassword)
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
