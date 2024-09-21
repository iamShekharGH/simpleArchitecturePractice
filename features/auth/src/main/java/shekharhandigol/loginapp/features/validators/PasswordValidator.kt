package shekharhandigol.loginapp.features.validators

import shekharhandigol.loginapp.common.util.InputValidator
import shekharhandigol.loginapp.common.util.ValidationResult
import shekharhandigol.loginapp.features.R

class PasswordValidator : InputValidator {
    override fun validate(input: String): ValidationResult {
        return if (input.length < 6) {
            ValidationResult(R.string.password_short)
        } else {
            ValidationResult()
        }
    }
}