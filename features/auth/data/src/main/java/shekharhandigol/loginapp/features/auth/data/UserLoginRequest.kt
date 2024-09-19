package shekharhandigol.loginapp.features.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(val email: String, val password: String)