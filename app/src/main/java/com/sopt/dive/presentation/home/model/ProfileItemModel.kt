package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable

@Immutable
data class ProfileItemModel(
    val badge: ProfileBadge,
    val nickname: String,
    val description: ProfileDescription,
    val actionType: ProfileActionType
)