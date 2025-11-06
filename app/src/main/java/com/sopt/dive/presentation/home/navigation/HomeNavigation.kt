package com.sopt.dive.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.navigation.MainTabRoute
import com.sopt.dive.presentation.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
data object Home : MainTabRoute

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(
        route = Home,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeNavGraph(
    paddingValues: PaddingValues,
    navigateToProfile: () -> Unit
) {
    composable<Home> {
        val context = LocalContext.current
        val userPrefs = remember { UserPreferences(context) }
        val userInfo = userPrefs.getUserInfo()

        HomeRoute(
            paddingValues = paddingValues,
            userInfo = userInfo,
            navigateToProfile = navigateToProfile
        )
    }
}
