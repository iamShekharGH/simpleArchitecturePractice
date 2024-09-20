package shekharhandigol.loginapp.features.auth.domain

import shekharhandigol.loginapp.features.auth.data.UserRepository
import shekharhandigol.loginapp.network.NetworkResult
import javax.inject.Inject

class UserDataUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {

    suspend fun invoke(): Resource<User> {
        return when (val result = userRepository.user()) {
            is NetworkResult.Failure -> result.toResultError()
            is NetworkResult.Success -> {
                Resource.Success(userMapper.map(result.result.data))
            }
        }

    }
}