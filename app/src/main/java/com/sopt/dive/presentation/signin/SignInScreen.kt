package com.sopt.dive.presentation.signin

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.designsystem.component.SoptBasicButton
import com.sopt.dive.core.designsystem.component.item.InputItem
import com.sopt.dive.core.designsystem.component.item.TextFieldType
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extension.advancedImePadding
import com.sopt.dive.core.extension.noRippleClickable
import com.sopt.dive.core.util.handleSignIn

@Composable
fun SignInRoute(
    onSignUpClick: () -> Unit,
    onSignInSuccess: () -> Unit
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    SignInScreen(
        id = id,
        password = password,
        onIdChange = { id = it },
        onPasswordChange = { password = it },
        onTextClick = onSignUpClick,
        onButtonClick = {
            handleSignIn(
                context = context,
                userPrefs = userPrefs,
                id = id,
                password = password,
                onSuccess = onSignInSuccess
            )
        }
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
    val focusManager = LocalFocusManager.current

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
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
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
