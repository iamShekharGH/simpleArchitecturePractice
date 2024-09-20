package shekharhandigol.loginapp.features.auth.data

import shekharhandigol.loginapp.network.NetworkResult
import shekharhandigol.loginapp.network.Response

interface UserRepository {
    suspend fun user(): NetworkResult<Response<UserApiModel>>
}