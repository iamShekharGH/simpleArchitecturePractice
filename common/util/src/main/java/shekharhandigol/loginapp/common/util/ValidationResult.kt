package shekharhandigol.loginapp.common.util

data class ValidationResult(
    val errorMessage: Int? = null
) {
    val isValid: Boolean
        get() = errorMessage == null
}