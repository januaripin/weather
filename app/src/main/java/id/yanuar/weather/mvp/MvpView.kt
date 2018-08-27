package id.yanuar.weather.mvp

import android.support.design.widget.Snackbar

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
interface MvpView{
    fun showMessage(message: String?, callback: Snackbar.Callback? = null)
}