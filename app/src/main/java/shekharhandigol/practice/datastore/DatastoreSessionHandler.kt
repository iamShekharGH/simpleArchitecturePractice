package shekharhandigol.practice.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shekharhandigol.loginapp.storage.CurrentUser
import shekharhandigol.loginapp.storage.SessionHandler
import shekharhandigol.loginapp.storage.User
import javax.inject.Inject

private val Context.userDatastore: DataStore<User> by dataStore(
    fileName = "user.pb",
    serializer = UserSerializer
)

class DatastoreSessionHandler @Inject constructor(
    @ApplicationContext private val context: Context
) : SessionHandler {
    override suspend fun setCurrentUser(id: Int, authKey: String?) {
        context.userDatastore.updateData {
            it.toBuilder().setAuthKey(authKey).setId(id).build()
        }
    }

    override fun getCurrentUser(): Flow<CurrentUser> {
        return context.userDatastore.data.map {
            CurrentUser(id = it.id, authKey = it.authKey)
        }
    }

    override suspend fun clearCurrentUser() {
        context.userDatastore.updateData {
            it.toBuilder().clear().build()
        }
    }
}

