package id.yanuar.weather.mvp

import io.reactivex.disposables.Disposable

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
open class DisposablePresenter<V : MvpView> : MvpPresenter<V>() {

    protected var disposable: Disposable? = null

    override fun detachView() {
        super.detachView()
        disposable?.run { if (isDisposed.not()) dispose() }
    }
}