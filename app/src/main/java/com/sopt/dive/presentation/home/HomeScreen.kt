package com.sopt.dive.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.data.UserInfo
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.extension.noRippleClickable
import com.sopt.dive.presentation.home.component.ProfileItem
import com.sopt.dive.presentation.home.model.ProfileActionType
import com.sopt.dive.presentation.home.model.ProfileBadge
import com.sopt.dive.presentation.home.model.ProfileDescription
import com.sopt.dive.presentation.home.model.ProfileItemModel

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    userInfo: UserInfo,
    navigateToProfile: () -> Unit
) {
    // 더미 데이터
    val profileItems = listOf(
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
        ),
        ProfileItemModel(
            badge = ProfileBadge.BIRTHDAY,
            nickname = "정하린",
            description = ProfileDescription.Exists("오늘은 저를 위한 하루! 💖"),
            actionType = ProfileActionType.Gift
        ),
        ProfileItemModel(
            badge = ProfileBadge.MEMORIAL,
            nickname = "윤서연",
            description = ProfileDescription.Exists("늘 마음속에 함께해요."),
            actionType = ProfileActionType.None
        ),
        ProfileItemModel(
            badge = ProfileBadge.NONE,
            nickname = "한지후",
            description = ProfileDescription.Exists("요즘 커피에 빠졌어요 ☕"),
            actionType = ProfileActionType.Music("Coffee - BTS")
        ),
        ProfileItemModel(
            badge = ProfileBadge.NONE,
            nickname = "오은서",
            description = ProfileDescription.Exists("오늘은 하늘이 정말 예뻐요 🌤️"),
            actionType = ProfileActionType.None
        ),
        ProfileItemModel(
            badge = ProfileBadge.BIRTHDAY,
            nickname = "김도윤",
            description = ProfileDescription.Exists("축하해주셔서 감사해요! 🎂"),
            actionType = ProfileActionType.Music("Happy - Pharrell Williams")
        )
    )

    HomeScreen(
        userInfo = userInfo,
        profileItems = profileItems,
        navigateToProfile = navigateToProfile,
        modifier = Modifier.padding(paddingValues)
    )
}


@Composable
private fun HomeScreen(
    userInfo: UserInfo,
    profileItems: List<ProfileItemModel>,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA)),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            MyProfileSection(
                userInfo = userInfo,
                modifier = Modifier.padding(bottom = 16.dp),
                navigateToProfile = navigateToProfile
            )
        }

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

@Composable
private fun MyProfileSection(
    userInfo: UserInfo,
    navigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .noRippleClickable(onClick = navigateToProfile),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clip(shape = CircleShape)
        )

        Text(text = "${userInfo.id} 님 😻")
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    DiveTheme {
        // 더미 데이터
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
            userInfo = UserInfo(
                id = "깜자",
                password = "1234",
                nickname = "1234",
                mbti = "ENFP"
            ),
            profileItems = sampleProfiles,
            navigateToProfile = {}
        )
    }
}
