package com.sopt.dive.core.designsystem.component.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SoptBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    maxLines: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
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
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun SoptTextFieldsCollectionPreview() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    SoptBasicTextField(
        value = username,
        onValueChange = { username = it },
        placeholder = "아이디",
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = {})
    )

    SoptBasicTextField(
        value = password,
        onValueChange = { password = it },
        placeholder = "비밀번호",
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {})
    )
}
