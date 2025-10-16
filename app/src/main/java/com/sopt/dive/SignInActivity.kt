package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.sopt.dive.core.component.SoptBasicButton
import com.sopt.dive.core.component.item.InputItem
import com.sopt.dive.core.component.item.TextFieldType
import com.sopt.dive.core.extension.noRippleClickable
import com.sopt.dive.ui.theme.DiveTheme

class SignInActivity : ComponentActivity() {
    private var registeredId: String = ""
    private var registeredPassword: String = ""
    private var registeredNickname: String = ""
    private var registeredMbti: String = ""

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            registeredId = data?.getStringExtra("id") ?: ""
            registeredPassword = data?.getStringExtra("password") ?: ""
            registeredNickname = data?.getStringExtra("nickname") ?: ""
            registeredMbti = data?.getStringExtra("mbti") ?: ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveTheme {
                SignInRoute(
                    onSignUpClick = {
                        val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                        signUpLauncher.launch(intent)
                    },
                    onSignInClick = { id, password ->
                        if (registeredId.isEmpty()) {
                            Toast.makeText(this@SignInActivity, "회원가입을 먼저 진행해주세요", Toast.LENGTH_SHORT).show()
                            return@SignInRoute
                        }

                        if (id == registeredId && password == registeredPassword) {
                            Toast.makeText(this@SignInActivity, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignInActivity, MainActivity::class.java).apply {
                                putExtra("id", id)
                                putExtra("password", password)
                                putExtra("nickname", registeredNickname)
                                putExtra("mbti", registeredMbti)
                            }
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@SignInActivity, "아이디 또는 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SignInRoute(
    onSignUpClick: () -> Unit,
    onSignInClick: (String, String) -> Unit
) {
    val (id, setId) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    SignInScreen(
        id = id,
        password = password,
        onIdChange = setId,
        onPasswordChange = setPassword,
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
