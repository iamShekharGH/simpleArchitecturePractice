package shekharhandigol.loginapp.storage

import kotlinx.coroutines.flow.Flow

interface SessionHandler {
    suspend fun setCurrentUser(id: Int, authKey: String?)
    fun getCurrentUser(): Flow<CurrentUser>
    suspend fun clearCurrentUser()
}

data class CurrentUser(
    val id: Int,
    val authKey: String
)