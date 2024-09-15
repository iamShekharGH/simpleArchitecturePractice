package shekharhandigol.loginapp.features

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import shekharhandigol.loginapp.features.splash.SplashScreen

const val navRoute = "auth"

sealed class AuthScreen(val route: String) {
    data object Splash : AuthScreen("$navRoute/splash")
    data object Login : AuthScreen("$navRoute/login")
    data object SignUp : AuthScreen("$navRoute/signup")
}

fun NavGraphBuilder.authNavBuilder(
    navController: NavController,
    onAuthSuccess: () -> Unit
) {
    navigation(
        startDestination = AuthScreen.Splash.route,
        route = navRoute
    ) {
        composable(AuthScreen.Splash.route) {
            SplashScreen()
        }
    }


}

