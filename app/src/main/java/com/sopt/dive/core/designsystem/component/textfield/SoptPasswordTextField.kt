package com.sopt.dive.core.designsystem.component.textfield

import android.R
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extension.noRippleClickable

private data class PasswordVisibilityState(
    val visualTransformation: VisualTransformation,
    val iconRes: Int
)

@Composable
fun SoptPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    maxLines: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val passwordState = remember(passwordVisible) {
        if (passwordVisible) {
            PasswordVisibilityState(
                visualTransformation = VisualTransformation.None,
                iconRes = R.drawable.ic_menu_view
            )
        } else {
            PasswordVisibilityState(
                visualTransformation = PasswordVisualTransformation(),
                iconRes = R.drawable.ic_secure
            )
        }
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.LightGray,
            focusedIndicatorColor = Color.Black,
            unfocusedPlaceholderColor = Color.Gray
        ),
        placeholder = { Text(text = placeholder) },
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = passwordState.visualTransformation,
        trailingIcon = {
            Icon(
                painter = painterResource(id = passwordState.iconRes),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .noRippleClickable { passwordVisible = !passwordVisible }
            )
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun SoptPasswordTextFieldPreview() {
    DiveTheme {
        var password by remember { mutableStateOf("") }

        SoptPasswordTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "비밀번호",
            maxLines = 1
        )
    }
}
