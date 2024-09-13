package shekharhandigol.practice.loginapp.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import shekharhandigol.practice.loginapp.theme.R

@Composable
fun AppTextField(
    text: String,
    @StringRes label: Int,
    @StringRes error: Int? = null,
    hint: String = "",
    isPassword: Boolean = false,
    isClickOnly: Boolean = false,
    onClick: () -> Unit = {},
    onValueChanged: (value: String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    leadingIcon: ImageVector? = null,
    onDone: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current



    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { if (isClickOnly) onClick() },
            value = text,
            onValueChange = { onValueChanged(it) },
            singleLine = true,
            readOnly = isClickOnly,
            enabled = !isClickOnly,
            supportingText = {
                if (error != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(error),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            label = { Text(text = stringResource(label)) },
            placeholder = { Text(text = hint) },
            leadingIcon = {
                leadingIcon?.let {
                    Icon(it, hint, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            },
            trailingIcon = {
                if (error != null) {
                    Icon(Icons.Filled.Info, "error", tint = MaterialTheme.colorScheme.error)
                }
            },

            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onDone()
                },
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
        )
    }
}

@DayNightPreview
@Composable
fun AppTextFieldPreview() {
    Surface {
        AppTextField(
            text = "This is some text",
            label = R.string.your_label,
            hint = "Enter some text here",
            onValueChanged = {},

        )
    }
}
