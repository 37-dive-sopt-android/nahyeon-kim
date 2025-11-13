package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.presentation.home.model.ProfileActionType
import com.sopt.dive.presentation.home.model.ProfileBadge
import com.sopt.dive.presentation.home.model.ProfileDescription

@Composable
fun ProfileItem(
    badge: ProfileBadge,
    nickname: String,
    description: ProfileDescription,
    actionType: ProfileActionType,
    avatarUrl: String,
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
        ProfileImage(badge = badge, avatarUrl = avatarUrl, modifier = Modifier.padding(end = 10.dp))
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
            ProfileImage(badge = ProfileBadge.BIRTHDAY, avatarUrl = "https://reqres.in/img/faces/1-image.jpg")
            ProfileImage(badge = ProfileBadge.MEMORIAL, avatarUrl = "https://reqres.in/img/faces/2-image.jpg")
            ProfileImage(badge = ProfileBadge.NONE, avatarUrl = "")

            ProfileInfo(nickname = "김나현", ProfileDescription.Exists("안녕하세요"))

            ProfileAction(actionType = ProfileActionType.Music("Ditto - NewJeans"))
            ProfileAction(actionType = ProfileActionType.Gift)
            ProfileAction(actionType = ProfileActionType.None)

            ProfileItem(
                badge = ProfileBadge.BIRTHDAY,
                nickname = "김나현",
                description = ProfileDescription.Exists("안녕하세요"),
                actionType = ProfileActionType.Music("Ditto - NewJeans"),
                avatarUrl = "https://reqres.in/img/faces/1-image.jpg"
            )
        }
    }
}