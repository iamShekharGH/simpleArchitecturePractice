package shekharhandigol.practice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import shekharhandigol.loginapp.features.authNavBuilder
import shekharhandigol.loginapp.features.home.Home
import shekharhandigol.loginapp.features.navRoute


@Composable
fun LoginPageNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = navRoute
    ) {
        authNavBuilder(
            navController = navHostController,
            onAuthSuccess = {
                navHostController.navigate("home") {
                    popUpTo(navRoute) {
                        inclusive = true
                    }
                }

            }
        )

        composable("home") {
            Home()
        }
    }
}