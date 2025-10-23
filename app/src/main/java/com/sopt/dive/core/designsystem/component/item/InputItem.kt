package com.sopt.dive.core.designsystem.component.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.designsystem.component.textfield.SoptBasicTextField
import com.sopt.dive.core.designsystem.component.textfield.SoptPasswordTextField

sealed class TextFieldType {
    object Basic : TextFieldType()
    object Password : TextFieldType()
}

@Composable
fun InputItem(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    type: TextFieldType = TextFieldType.Basic,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        when (type) {
            TextFieldType.Basic -> {
                SoptBasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = placeholder,
                    maxLines = 1,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            TextFieldType.Password -> {
                SoptPasswordTextField(
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = placeholder,
                    maxLines = 1,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
