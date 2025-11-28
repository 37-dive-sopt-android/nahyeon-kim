package com.sopt.dive.presentation.home

import androidx.compose.runtime.Immutable
import com.sopt.dive.presentation.home.model.ProfileActionType
import com.sopt.dive.presentation.home.model.ProfileBadge
import com.sopt.dive.presentation.home.model.ProfileDescription
import com.sopt.dive.presentation.home.model.ProfileItemModel

@Immutable
data class HomeUiState(
    val name: String = "",
    val profileItems: List<ProfileItemModel> = emptyList()
) {
    companion object {
        val Fake = HomeUiState(
            name = "나현",
            profileItems = listOf(
                ProfileItemModel(
                    badge = ProfileBadge.BIRTHDAY,
                    nickname = "George Bluth",
                    description = ProfileDescription.Exists("오늘 생일이에요! 🎉"),
                    actionType = ProfileActionType.Music("Super Shy - NewJeans"),
                    avatarUrl = "https://reqres.in/img/faces/1-image.jpg"
                ),
                ProfileItemModel(
                    badge = ProfileBadge.MEMORIAL,
                    nickname = "Janet Weaver",
                    description = ProfileDescription.Exists("항상 그리워요."),
                    actionType = ProfileActionType.None,
                    avatarUrl = "https://reqres.in/img/faces/2-image.jpg"
                ),
                ProfileItemModel(
                    badge = ProfileBadge.NONE,
                    nickname = "Emma Wong",
                    description = ProfileDescription.None,
                    actionType = ProfileActionType.Gift,
                    avatarUrl = "https://reqres.in/img/faces/3-image.jpg"
                ),
                ProfileItemModel(
                    badge = ProfileBadge.NONE,
                    nickname = "Eve Holt",
                    description = ProfileDescription.Exists("요즘엔 산책이 좋아요"),
                    actionType = ProfileActionType.Music("Love Lee - AKMU"),
                    avatarUrl = "https://reqres.in/img/faces/4-image.jpg"
                ),
                ProfileItemModel(
                    badge = ProfileBadge.BIRTHDAY,
                    nickname = "Charles Morris",
                    description = ProfileDescription.Exists("오늘은 저를 위한 하루! 💖"),
                    actionType = ProfileActionType.Gift,
                    avatarUrl = "https://reqres.in/img/faces/5-image.jpg"
                ),
                ProfileItemModel(
                    badge = ProfileBadge.MEMORIAL,
                    nickname = "Tracey Ramos",
                    description = ProfileDescription.Exists("늘 마음속에 함께해요."),
                    actionType = ProfileActionType.None,
                    avatarUrl = "https://reqres.in/img/faces/6-image.jpg"
                )
            )
        )
    }
}