package shekharhandigol.loginapp.features.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()


    fun onEvent(ui: LoginScreenEvents) {
        when (ui) {
            LoginScreenEvents.ForgotPassword -> {}
            LoginScreenEvents.Login -> {}
            LoginScreenEvents.SignUp -> {}
            is LoginScreenEvents.PasswordInputted -> {
                _loginUiState.value = _loginUiState.value.copy(password = ui.password)
            }

            is LoginScreenEvents.UsernameInputted -> {
                _loginUiState.value = _loginUiState.value.copy(username = ui.uname)
            }
        }

    }
}