package shekharhandigol.loginapp.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shekharhandigol.loginapp.features.auth.domain.LoginUseCase
import shekharhandigol.loginapp.features.validators.AuthParams
import shekharhandigol.loginapp.features.validators.ValidatorFactory
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validatorFactory: ValidatorFactory
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()


    fun onEvent(ui: LoginScreenEvents) {
        when (ui) {
            LoginScreenEvents.ForgotPassword -> {}
            LoginScreenEvents.Login -> {
                if (areInputsValid())
                    login()
            }

            LoginScreenEvents.SignUp -> {}
            is LoginScreenEvents.PasswordInputted -> {
                _loginUiState.value = _loginUiState.value.copy(password = ui.password)
            }

            is LoginScreenEvents.UsernameInputted -> {
                _loginUiState.value = _loginUiState.value.copy(username = ui.uname)
            }
        }

    }

    private fun areInputsValid(): Boolean {
        val emailError =
            validatorFactory.get(AuthParams.EMAIL).validate(loginUiState.value.username)
        val passwordError =
            validatorFactory.get(AuthParams.PASSWORD).validate(loginUiState.value.password)

        _loginUiState.value = _loginUiState.value.copy(
            emailError = emailError.errorMessage,
            passwordError = passwordError.errorMessage
        )
        val hasError = listOf(emailError, passwordError).any { it.isValid.not() }
        return hasError.not()
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase.invoke(
                email = loginUiState.value.username,
                password = loginUiState.value.password
            )
        }
    }
}