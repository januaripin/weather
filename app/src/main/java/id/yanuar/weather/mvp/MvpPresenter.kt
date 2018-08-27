package id.yanuar.weather.mvp

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

abstract class MvpPresenter<V : MvpView> {
    protected var view: V? = null
    open fun attachView(view: V) {
        this.view = view
    }

    open fun detachView() {
        view = null
    }
}