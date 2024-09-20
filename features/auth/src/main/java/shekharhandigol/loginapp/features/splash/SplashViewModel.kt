package shekharhandigol.loginapp.features.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shekharhandigol.loginapp.features.auth.domain.Resource
import shekharhandigol.loginapp.features.auth.domain.UserDataUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: UserDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Splash())
    val uiState = _uiState.asStateFlow()

    init {
        isLoggedIn()
    }

    private fun isLoggedIn() {
        viewModelScope.launch {
            _uiState.value = SplashUiState.Splash(isLoading = true)
            when (useCase.invoke()) {
                is Resource.Error -> _uiState.value = SplashUiState.Splash(moveToLogin = true)
                is Resource.Success -> _uiState.value = SplashUiState.Authenticates
            }
        }
    }


}

sealed class SplashUiState {
    data object Authenticates : SplashUiState()
    data class Splash(
        val isLoading: Boolean = false,
        val moveToLogin: Boolean = false,
    ) : SplashUiState()
}