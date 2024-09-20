package shekharhandigol.loginapp.features.auth.data

import shekharhandigol.loginapp.network.NetworkResult
import shekharhandigol.loginapp.network.RequestHandler
import shekharhandigol.loginapp.network.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val requestHandler: RequestHandler
) : UserRepository {
    override suspend fun user(): NetworkResult<Response<UserApiModel>> {
        return requestHandler.get(urlPathSegments = listOf("user"))
    }
}