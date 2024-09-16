package shekharhandigol.loginapp.features.login

sealed class LoginScreenEvents {
    data class UsernameInputted(val uname: String) : LoginScreenEvents()
    data class PasswordInputted(val password: String) : LoginScreenEvents()
    data object Login : LoginScreenEvents()
    data object ForgotPassword : LoginScreenEvents()
    data object SignUp : LoginScreenEvents()
}