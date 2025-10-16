package com.sopt.dive.core.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputItem(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier
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
        SoptBasicTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            maxLines = 1,
            isPassWord = isPassword,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
