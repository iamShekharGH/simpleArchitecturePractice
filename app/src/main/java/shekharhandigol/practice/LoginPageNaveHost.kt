package shekharhandigol.practice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import shekharhandigol.loginapp.features.authNavBuilder
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

            }
        )
    }

}