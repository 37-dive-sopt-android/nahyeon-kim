package com.sopt.dive.core.designsystem.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.theme.DiveTheme

sealed class ProfileActionType {
    data class Music(val songTitle: String) : ProfileActionType()
    data object Gift : ProfileActionType()
    data object Remember : ProfileActionType()
    data object None : ProfileActionType()
}

sealed class ProfileDescription {
    data class Exists(val text: String) : ProfileDescription()
    data object None : ProfileDescription()
}

enum class ProfileBadge {
    BIRTHDAY,
    MEMORIAL,
    NONE
}

@Composable
fun ProfileImage(
    badge: ProfileBadge,
    modifier: Modifier = Modifier,
) {
    val badgeIcon = remember {
        when (badge) {
            ProfileBadge.BIRTHDAY -> R.drawable.ic_profile_birthday
            ProfileBadge.MEMORIAL -> R.drawable.ic_profile_memorial
            ProfileBadge.NONE -> null
        }
    }

    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .clip(shape = CircleShape)
                .border(
                    shape = CircleShape,
                    color = Color.LightGray,
                    width = 1.dp
                )
        )
        badgeIcon?.let {
            Icon(
                imageVector = ImageVector.vectorResource(id = it),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.BottomEnd)

            )
        }
    }
}

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

@Composable
fun ProfileItem(
    badge: ProfileBadge,
    nickname: String,
    description: ProfileDescription,
    actionType: ProfileActionType,
    modifier: Modifier = Modifier
) {

    val effectiveActionType = remember(badge, actionType) {
        when (badge) {
            ProfileBadge.BIRTHDAY -> ProfileActionType.Gift
            ProfileBadge.MEMORIAL -> ProfileActionType.Remember
            else -> actionType
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ProfileImage(badge = badge, modifier = Modifier.padding(end = 10.dp))
        ProfileInfo(nickname = nickname, description = description)
        Spacer(modifier = Modifier.weight(1f))
        ProfileAction(actionType = effectiveActionType)
    }
}


@Preview(showBackground = true)
@Composable
private fun ProfileItemPreview() {
    DiveTheme {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            ProfileImage(badge = ProfileBadge.BIRTHDAY)
            ProfileImage(badge = ProfileBadge.MEMORIAL)
            ProfileImage(badge = ProfileBadge.NONE)

            ProfileInfo(nickname = "김나현", ProfileDescription.Exists("안녕하세요"))

            ProfileAction(actionType = ProfileActionType.Music("Ditto - NewJeans"))
            ProfileAction(actionType = ProfileActionType.Gift)
            ProfileAction(actionType = ProfileActionType.None)

            ProfileItem(
                badge = ProfileBadge.BIRTHDAY,
                nickname = "김나현",
                description = ProfileDescription.Exists("안녕하세요"),
                actionType = ProfileActionType.Music("Ditto - NewJeans")
            )
        }
    }
}