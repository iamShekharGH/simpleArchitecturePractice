package shekharhandigol.loginapp.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shekharhandigol.loginapp.features.R
import shekharhandigol.loginapp.features.auth.domain.LoginUseCase
import shekharhandigol.loginapp.features.auth.domain.Resource
import shekharhandigol.loginapp.features.auth.domain.ResourceError
import shekharhandigol.loginapp.features.validators.AuthParams
import shekharhandigol.loginapp.features.validators.ValidatorFactory
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validatorFactory: ValidatorFactory
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.NotAuthenticated())
    val loginUiState = _loginUiState.asStateFlow()


    fun onEvent(ui: LoginScreenEvents) {
        when (ui) {
            LoginScreenEvents.ForgotPassword -> {}
            LoginScreenEvents.Login -> {
                if (areInputsValid())
                    login()
            }

            LoginScreenEvents.SignUp -> {}
            is LoginScreenEvents.PasswordInputted -> updateState { it.copy(password = ui.password) }

            is LoginScreenEvents.UsernameInputted -> updateState { it.copy(username = ui.uname) }
        }

    }

    private fun updateState(update: (LoginUiState.NotAuthenticated) -> LoginUiState.NotAuthenticated) {
        _loginUiState.value = (_loginUiState.value as? LoginUiState.NotAuthenticated)?.let(update)
            ?: _loginUiState.value

    }

    private fun areInputsValid(): Boolean {
        (_loginUiState.value as? LoginUiState.NotAuthenticated)?.let { ui ->
            val emailError =
                validatorFactory.get(AuthParams.EMAIL).validate(ui.username)
            val passwordError =
                validatorFactory.get(AuthParams.PASSWORD).validate(ui.password)
            updateState {
                it.copy(
                    emailError = emailError.errorMessage,
                    passwordError = passwordError.errorMessage
                )
            }
            return listOf(emailError, passwordError).any { it.isValid.not() }.not()
        }
        return false
    }

    fun login() {
        viewModelScope.launch {
            (_loginUiState.value as? LoginUiState.NotAuthenticated)?.let { ui ->
                updateState { it.copy(isLoading = true) }
                _loginUiState.value = when (val loginResult = loginUseCase.invoke(
                    email = ui.username,
                    password = ui.password
                )) {
                    is Resource.Error -> ui.copy(loginError = getError(loginResult))
                    is Resource.Success -> LoginUiState.Authenticated
                }
            }
        }
    }

    private fun getError(loginError: Resource.Error): Int {
        return when (loginError.e) {
            ResourceError.UNAUTHORIZED -> R.string.invalid_email_password
            ResourceError.SERVICE_UNAVAILABLE -> R.string.service_unavailable
            ResourceError.UNKNOWN -> R.string.unknown_error
        }
    }
}