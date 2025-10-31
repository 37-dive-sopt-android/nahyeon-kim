package com.sopt.dive.presentation.home.model

sealed class ProfileActionType {
    data class Music(val songTitle: String) : ProfileActionType()
    data object Gift : ProfileActionType()
    data object Remember : ProfileActionType()
    data object None : ProfileActionType()
}