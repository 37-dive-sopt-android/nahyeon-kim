package com.sopt.dive.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.sopt.dive.core.data.UserPreferences
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.presentation.home.navigation.Home
import com.sopt.dive.presentation.home.navigation.homeNavGraph
import com.sopt.dive.presentation.main.component.MainBottomBar
import com.sopt.dive.presentation.main.state.MainAppState
import com.sopt.dive.presentation.main.state.rememberMainAppState
import com.sopt.dive.presentation.mypage.navigation.myPageNavGraph
import com.sopt.dive.presentation.search.navigation.searchNavGraph
import com.sopt.dive.presentation.signin.navigation.SignIn
import com.sopt.dive.presentation.signin.navigation.signInNavGraph
import com.sopt.dive.presentation.signup.navigation.signUpNavGraph

class MainActivity : ComponentActivity() {
    private lateinit var userPrefs: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        userPrefs = UserPreferences(this)

        setContent {
            DiveTheme {
                val startDestination = if (userPrefs.isSignedIn()) Home else SignIn
                val appState = rememberMainAppState(startDestination = startDestination)

                MainScreen(appState = appState)
            }
        }
    }
}

@Composable
fun MainScreen(
    appState: MainAppState
) {
    val isBottomBarVisible by appState.isBottomBarVisible.collectAsStateWithLifecycle()
    val currentTab by appState.currentTab.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MainBottomBar(
                visible = isBottomBarVisible,
                tabs = MainTab.entries.toList(),
                currentTab = currentTab,
                onTabSelected = appState::navigate
            )
        }
    ) { innerPadding ->
        NavHost(
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
            navController = appState.navController,
            startDestination = appState.startDestination,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            signInNavGraph(
                paddingValues = innerPadding,
                navigateToHome = appState::navigateToHome,
                navigateToSignUp = appState::navigateToSignUp
            )

            signUpNavGraph(
                paddingValues = innerPadding,
                navigateToSignIn = appState::navigateToSignIn
            )

            homeNavGraph(
                paddingValues = innerPadding,
                navigateToProfile = appState::navigateToMyPage
            )

            searchNavGraph(
                paddingValues = innerPadding
            )

            myPageNavGraph(
                paddingValues = innerPadding,
                navigateToSignIn = appState::navigateToSignIn
            )
        }
    }
}