package shekharhandigol.practice.loginapp.theme.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import shekharhandigol.practice.loginapp.theme.LoginPageTheme


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(
    title: String,
    icon: ImageVector? = null,
    onIconClick: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = { Text(title) },
        navigationIcon = {
            icon?.let {
                IconButton(onClick = { onIconClick() }) {
                    Icon(icon, contentDescription = "Navigation icon clicked.")
                }
            }
        },
    )
}

@DayNightPreview
@Composable
private fun AppBarPreview() {
    LoginPageTheme {
        Surface {
            AppBar(title = "Login App", icon = Icons.AutoMirrored.Filled.ArrowBack)
        }
    }

}