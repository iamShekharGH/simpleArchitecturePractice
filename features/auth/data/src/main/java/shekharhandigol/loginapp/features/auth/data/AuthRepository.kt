package shekharhandigol.loginapp.features.auth.data

import shekharhandigol.loginapp.network.NetworkResult
import shekharhandigol.loginapp.network.Response

interface AuthRepository {
    suspend fun login(request: UserLoginRequest): NetworkResult<Response<UserApiModel>>
}

