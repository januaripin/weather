package id.yanuar.weather.mvp

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
open class CompositeDisposablePresenter<V : MvpView> : MvpPresenter<V>() {
    var compositeDisposable = CompositeDisposable()

    override fun detachView() {
        super.detachView()
        if (compositeDisposable.isDisposed.not()) compositeDisposable.dispose()
    }
}