package com.sopt.dive.presentation.home

import com.sopt.dive.core.data.UserInfo
import com.sopt.dive.presentation.home.model.ProfileActionType
import com.sopt.dive.presentation.home.model.ProfileBadge
import com.sopt.dive.presentation.home.model.ProfileDescription
import com.sopt.dive.presentation.home.model.ProfileItemModel

data class HomeUiState(
    val userInfo: UserInfo,
    val profileItems: List<ProfileItemModel>
) {
    companion object {
        val Fake = HomeUiState(
            userInfo = UserInfo(
                id = "깜자",
                password = "1234",
                nickname = "나현",
                mbti = "INFJ"
            ),
            profileItems = listOf(
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
        )
    }
}