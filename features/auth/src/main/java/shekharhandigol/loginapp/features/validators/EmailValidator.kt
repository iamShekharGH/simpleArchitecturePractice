package shekharhandigol.loginapp.features.validators

import shekharhandigol.loginapp.common.util.InputValidator
import shekharhandigol.loginapp.common.util.ValidationResult
import shekharhandigol.loginapp.features.R

class EmailValidator : InputValidator {

    private val emailPattern = Regex(
        "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,}$",
        RegexOption.IGNORE_CASE,
    )

    override fun validate(input: String): ValidationResult {
        return if (input.isEmpty()) {
            ValidationResult(R.string.email_cannot_be_empty)
        } else if (emailPattern.matches(input).not()) {
            ValidationResult(R.string.email_is_invalid)
        } else {
            ValidationResult()
        }
    }
}