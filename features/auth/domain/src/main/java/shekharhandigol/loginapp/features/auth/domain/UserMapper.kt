package shekharhandigol.loginapp.features.auth.domain

import shekharhandigol.loginapp.features.auth.data.UserApiModel
import javax.inject.Inject

interface Mapper<F, T> {
    fun map(from: F): T
}

class UserMapper @Inject constructor(): Mapper<UserApiModel, User> {
    override fun map(from: UserApiModel): User {
        return User(
            avatar = from.avatar,
            createsAt = from.createdAt,
            email = from.email,
            fullName = from.fullName,
            id = from.id
        )
    }
}