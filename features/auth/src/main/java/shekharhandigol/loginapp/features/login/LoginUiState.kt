package shekharhandigol.loginapp.features.login

import androidx.annotation.StringRes

sealed class LoginUiState {

    data class NotAuthenticated(
        val username: String = "",
        val password: String = "",

        @StringRes val emailError: Int? = null,
        @StringRes val passwordError: Int? = null,

        val isLoading: Boolean = false,

        @StringRes val loginError: Int? = null,
    ) : LoginUiState()

    data object Authenticated : LoginUiState()
}
