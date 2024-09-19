package shekharhandigol.loginapp.features.auth.domain

import shekharhandigol.loginapp.features.auth.data.AuthRepository
import shekharhandigol.loginapp.features.auth.data.UserLoginRequest
import shekharhandigol.loginapp.network.NetworkException
import shekharhandigol.loginapp.network.NetworkResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: UserMapper
) {

    suspend fun invoke(email: String, password: String): Resource<User> {
        val request = UserLoginRequest(email, password)
        return when (val result = authRepository.login(request)) {
            is NetworkResult.Failure -> result.toResultError()
            is NetworkResult.Success -> Resource.Success(mapper.map(result.result.data))
        }
    }
}


fun NetworkResult.Failure<*>.toResultError(): Resource.Error {
    return when (exception) {
        is NetworkException.UnknownException -> Resource.Error(ResourceError.UNKNOWN)
        is NetworkException.UnauthorizedException -> Resource.Error(ResourceError.UNAUTHORIZED)
        is NetworkException.NotFoundException -> Resource.Error(ResourceError.SERVICE_UNAVAILABLE)
        else -> Resource.Error(ResourceError.UNKNOWN)
    }
}

