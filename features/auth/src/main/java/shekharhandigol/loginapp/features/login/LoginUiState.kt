package shekharhandigol.loginapp.features.login

import androidx.annotation.StringRes

data class LoginUiState(
    val username: String = "",
    val password: String = "",

    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null
)
