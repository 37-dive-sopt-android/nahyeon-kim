package com.sopt.dive.presentation.main.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.dive.presentation.home.navigation.navigateToHome
import com.sopt.dive.presentation.main.MainTab
import com.sopt.dive.presentation.mypage.navigation.navigateToMyPage
import com.sopt.dive.presentation.search.navigation.navigateToSearch
import com.sopt.dive.presentation.signin.navigation.navigateToSignIn
import com.sopt.dive.presentation.signup.navigation.navigateToSignUp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class MainAppState(
    val navController: NavHostController,
    val startDestination: Any,
    coroutineScope: CoroutineScope
) {
    private val currentDestination = navController.currentBackStackEntryFlow
        .map { it.destination }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val currentTab: StateFlow<MainTab?> = currentDestination
        .map { destination ->
            MainTab.find { tab ->
                destination?.hasRoute(tab.route::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    val isBottomBarVisible: StateFlow<Boolean> = currentDestination
        .map { destination ->
            MainTab.contains { tab ->
                destination?.hasRoute(tab.route::class) == true
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.SEARCH -> navController.navigateToSearch(navOptions)
            MainTab.MY_PAGE -> navController.navigateToMyPage(navOptions)
        }
    }

    private val clearStackNavOptions = navOptions {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }

    fun navigateToSignIn(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToSignIn(navOptions)
    }

    fun navigateToSignUp(navOptions: NavOptions? = null) {
        navController.navigateToSignUp(navOptions)
    }

    fun navigateToHome(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToHome(navOptions)
    }

    fun navigateToMyPage(navOptions: NavOptions? = clearStackNavOptions) {
        navController.navigateToMyPage(navOptions)
    }
}

@Composable
fun rememberMainAppState(
    startDestination: Any,
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): MainAppState = remember(navController, startDestination, coroutineScope) {
    MainAppState(navController, startDestination, coroutineScope)
}
