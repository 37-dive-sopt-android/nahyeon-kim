package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.sopt.dive.presentation.home.model.ProfileDescription

@Composable
fun ProfileInfo(
    nickname: String,
    description: ProfileDescription,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = nickname,
            color = Color.Black,
            fontSize = 12.sp,
            modifier = Modifier.height(IntrinsicSize.Min)
        )
        when (description) {
            is ProfileDescription.Exists -> Text(
                text = description.text, color = Color.Gray, fontSize = 10.sp,
                modifier = Modifier.height(IntrinsicSize.Min)
            )

            is ProfileDescription.None -> {}
        }
    }
}