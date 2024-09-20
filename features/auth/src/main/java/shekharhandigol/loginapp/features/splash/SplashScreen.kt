package shekharhandigol.loginapp.features.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import shekharhandigol.loginapp.features.AuthScreen
import shekharhandigol.loginapp.features.R
import shekharhandigol.practice.loginapp.theme.components.DayNightPreview


@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navController: NavController,
    onAuthSuccess: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (val state = uiState.value) {
        SplashUiState.Authenticates -> {
            LaunchedEffect(Unit) {
                onAuthSuccess()
            }
        }

        is SplashUiState.Splash -> {
            if (state.moveToLogin) {
                navController.navigate(AuthScreen.Login.route) {
                    popUpTo(AuthScreen.Splash.route) {
                        inclusive = true
                    }
                }

            } else {
                Splash()
            }
        }
    }
}

@Composable
fun Splash(
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(R.drawable.loginicon),
            contentDescription = "Splash screen icon"
        )
    }
}


@DayNightPreview
@Composable
fun SplashScreenPreview() {
    Splash()
}