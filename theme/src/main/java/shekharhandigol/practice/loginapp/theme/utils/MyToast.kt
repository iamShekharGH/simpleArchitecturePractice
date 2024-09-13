package shekharhandigol.practice.loginapp.theme.utils

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.R)
fun Context.showToast(message: String, toastIsShowing: (Boolean) -> Unit) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).also {
        it.addCallback(object : Toast.Callback() {
            override fun onToastHidden() {
                super.onToastHidden()
                toastIsShowing(false)
            }

            override fun onToastShown() {
                super.onToastShown()
                toastIsShowing(true)
            }
        })
    }.show()
}