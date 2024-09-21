package shekharhandigol.loginapp.features.validators

import shekharhandigol.loginapp.common.util.InputValidator
import javax.inject.Inject

class ValidatorFactory @Inject constructor() {
    private val validators: Map<AuthParams, InputValidator> = mapOf(
        AuthParams.EMAIL to EmailValidator(),
        AuthParams.PASSWORD to PasswordValidator()
    )

    fun get(params: AuthParams): InputValidator {
        return validators[params] ?: throw IllegalArgumentException("Validator Not found")
    }
}

enum class AuthParams {
    EMAIL, PASSWORD
}