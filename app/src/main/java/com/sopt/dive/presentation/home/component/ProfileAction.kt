package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.presentation.home.model.ProfileActionType

@Composable
fun ProfileAction(
    actionType: ProfileActionType,
    modifier: Modifier = Modifier
) {
    when (actionType) {
        is ProfileActionType.Music ->
            ActionTag(
                text = actionType.songTitle,
                color = Color.Green,
                modifier = modifier
            )
        is ProfileActionType.Gift ->
            ActionTag(
                text = "선물하기",
                color = Color.Red,
                modifier = modifier
            )
        is ProfileActionType.Remember ->
            ActionTag(
                text = "보고싶어요",
                color = Color.Gray,
                modifier = modifier
            )
        is ProfileActionType.None -> { }
    }
}

@Composable
private fun ActionTag(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        fontSize = 10.sp,
        modifier = modifier
            .clip(CircleShape)
            .background(Color.White)
            .border(
                shape = CircleShape,
                color = color,
                width = 1.dp
            )
            .padding(vertical = 1.dp, horizontal = 6.dp)
    )
}