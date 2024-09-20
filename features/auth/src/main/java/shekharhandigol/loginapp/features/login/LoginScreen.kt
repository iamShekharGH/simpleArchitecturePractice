package shekharhandigol.loginapp.features.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shekharhandigol.loginapp.features.R
import shekharhandigol.practice.loginapp.theme.R.string
import shekharhandigol.practice.loginapp.theme.components.AppTextField
import shekharhandigol.practice.loginapp.theme.components.DayNightPreview

@Composable
fun LoginScreen(viewModel: LoginScreenViewModel) {
    val uiState = viewModel.loginUiState.collectAsStateWithLifecycle()
    Login(
        uiState = uiState.value,
        onEvent = {
            viewModel.onEvent(it)
        })

}

@Composable
fun Login(
    onEvent: (LoginScreenEvents) -> Unit,
    uiState: LoginUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Icon(painter = painterResource(R.drawable.loginicon), contentDescription = "App Icon")
        AppTextField(
            text = uiState.username,
            label = string.your_label,
            hint = "email@domain.com",
            leadingIcon = Icons.Filled.Email,
            imeAction = ImeAction.Next,
            onValueChanged = {
                onEvent(LoginScreenEvents.UsernameInputted(it))
            }
        )
        AppTextField(
            text = uiState.password,
            label = string.password,
            isPassword = true,
            hint = "******",
            imeAction = ImeAction.Done,
            leadingIcon = Icons.Filled.Lock,
            onValueChanged = {
                onEvent(LoginScreenEvents.PasswordInputted(it))

            }
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                Text(
                    text = "Forgot Password",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "Click here to reset",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                onClick = {
                    onEvent.invoke(LoginScreenEvents.Login)
                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Login"
                )
            }
        }

        Column(
            modifier = Modifier.clickable {

            }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp),
                text = "Don't have an account? Sign up. duh.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Terms and conditions you have accepted by opening this app. Good Luck!",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center

            )
        }
    }

}

@DayNightPreview
@Composable
fun LoginPreview() {
    Login(
        onEvent = {},
        uiState = LoginUiState("username", "password")
    )
}