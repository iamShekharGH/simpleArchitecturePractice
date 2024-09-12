package shekharhandigol.practice.loginapp.theme.components

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Day",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Night",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
annotation class DayNightPreview