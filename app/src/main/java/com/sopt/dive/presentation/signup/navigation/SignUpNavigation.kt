package com.sopt.dive.presentation.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.presentation.signup.SignUpRoute
import kotlinx.serialization.Serializable

@Serializable
data object SignUp

fun NavController.navigateToSignUp(
    navOptions: NavOptions? = null
) {
    navigate(
        route = SignUp,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signUpNavGraph(
    navigateToSignIn: () -> Unit
) {
    composable<SignUp> {
        SignUpRoute(
            onSignUpSuccess = navigateToSignIn
        )
    }
}