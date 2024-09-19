package shekharhandigol.loginapp.features.auth.data

import shekharhandigol.loginapp.network.NetworkResult
import shekharhandigol.loginapp.network.RequestHandler
import shekharhandigol.loginapp.network.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val requestHandler: RequestHandler
) : AuthRepository {
    override suspend fun login(request: UserLoginRequest): NetworkResult<Response<UserApiModel>> {
        return requestHandler.post(
            urlPathSegments = listOf("auth", "login"),
            body = request
        )
    }
}