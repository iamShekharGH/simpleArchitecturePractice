package shekharhandigol.loginapp.network


sealed class NetworkResult<out T> {

    data class Success<T>(val result: T) : NetworkResult<T>()
    data class Failure<Nothing>(val body: String?, val exception: Exception) :
        NetworkResult<Nothing>()
}