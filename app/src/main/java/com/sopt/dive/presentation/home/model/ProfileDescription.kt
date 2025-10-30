package com.sopt.dive.presentation.home.model

sealed class ProfileDescription {
    data class Exists(val text: String) : ProfileDescription()
    data object None : ProfileDescription()
}