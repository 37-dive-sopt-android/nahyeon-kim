package com.sopt.dive.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.designsystem.component.item.ProfileActionType
import com.sopt.dive.core.designsystem.component.item.ProfileBadge
import com.sopt.dive.core.designsystem.component.item.ProfileDescription
import com.sopt.dive.core.designsystem.component.item.ProfileItem
import com.sopt.dive.core.designsystem.theme.DiveTheme

@Immutable
data class ProfileItemModel(
    val badge: ProfileBadge,
    val nickname: String,
    val description: ProfileDescription,
    val actionType: ProfileActionType
)

@Composable
private fun HomeScreen(
    profileItems: List<ProfileItemModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA)),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = profileItems,
            key = { it.nickname }
        ) { profile ->
            with(profile) {
                ProfileItem(
                    badge = badge,
                    nickname = nickname,
                    description = description,
                    actionType = actionType
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DiveTheme {
        val sampleProfiles = listOf(
            ProfileItemModel(
                badge = ProfileBadge.BIRTHDAY,
                nickname = "김나현",
                description = ProfileDescription.Exists("오늘 생일이에요! 🎉"),
                actionType = ProfileActionType.Music("Super Shy - NewJeans")
            ),
            ProfileItemModel(
                badge = ProfileBadge.MEMORIAL,
                nickname = "이서준",
                description = ProfileDescription.Exists("항상 그리워요."),
                actionType = ProfileActionType.None
            ),
            ProfileItemModel(
                badge = ProfileBadge.NONE,
                nickname = "최지우",
                description = ProfileDescription.None,
                actionType = ProfileActionType.Gift
            ),
            ProfileItemModel(
                badge = ProfileBadge.NONE,
                nickname = "박지민",
                description = ProfileDescription.Exists("요즘엔 산책이 좋아요"),
                actionType = ProfileActionType.Music("Love Lee - AKMU")
            )
        )

        HomeScreen(
            profileItems = sampleProfiles
        )
    }
}
