package id.yanuar.weather.util

import android.support.design.widget.Snackbar
import android.view.View

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
object SnackbarUtils {
    fun create(view: View?, message: String?, callback: Snackbar.Callback? = null, duration: Int = Snackbar.LENGTH_SHORT) {
        if (view == null || message == null) {
            return
        }

        val snackbar = Snackbar.make(view, message, duration)
        callback?.run {
            snackbar.addCallback(this)
        }
        snackbar.show()
    }
}