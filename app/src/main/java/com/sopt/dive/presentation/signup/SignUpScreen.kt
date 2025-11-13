package com.sopt.dive.presentation.signup

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.core.designsystem.component.SoptBasicButton
import com.sopt.dive.core.designsystem.component.item.InputItem
import com.sopt.dive.core.designsystem.component.item.TextFieldType
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.util.UiState

@Composable
fun SignUpRoute(
    onSignUpSuccess: () -> Unit,
    viewModel: SignUpViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    when (uiState) {
        is UiState.Success -> {
            val data = (uiState as UiState.Success<SignUpUiState>).data

            LaunchedEffect(data.signUpSuccessName) {
                data.signUpSuccessName?.let { name -> Toast.makeText(context, "회원가입 성공! ${name}님 환영합니다.", Toast.LENGTH_SHORT
                    ).show()
                    onSignUpSuccess()
                    viewModel.resetSignUpState()
                }
            }

            SignUpScreen(
                username = data.username,
                password = data.password,
                name = data.name,
                email = data.email,
                age = data.age,
                isValid = data.isValid,
                onUsernameChange = viewModel::updateUsername,
                onPasswordChange = viewModel::updatePassword,
                onNameChange = viewModel::updateName,
                onEmailChange = viewModel::updateEmail,
                onAgeChange = viewModel::updateAge,
                onSignUpClick = viewModel::signUp
            )
        }
        is UiState.Failure -> {
            LaunchedEffect(Unit) { Toast.makeText(context, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                viewModel.resetSignUpState()
            }
        }
        else -> {}
    }
}

@Composable
private fun SignUpScreen(
    username: String,
    password: String,
    name: String,
    email: String,
    age: String,
    isValid: Boolean,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
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
            label = "Username",
            value = username,
            onValueChange = onUsernameChange,
            placeholder = "사용자명을 입력해주세요",
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
            )
        )

        InputItem(
            label = "Name",
            value = name,
            onValueChange = onNameChange,
            placeholder = "이름을 입력해주세요",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        InputItem(
            label = "Email",
            value = email,
            onValueChange = onEmailChange,
            placeholder = "이메일을 입력해주세요",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        InputItem(
            label = "Age",
            value = age,
            onValueChange = onAgeChange,
            placeholder = "나이를 입력해주세요",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    if (isValid) onSignUpClick()
                }
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        SoptBasicButton(
            title = "회원가입하기",
            onClick = onSignUpClick
        )
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(
            username = "",
            password = "",
            name = "",
            email = "",
            age = "",
            isValid = false,
            onUsernameChange = {},
            onPasswordChange = {},
            onNameChange = {},
            onEmailChange = {},
            onAgeChange = {},
            onSignUpClick = {}
        )
    }
}
