package id.yanuar.weather.presentation.weather

import id.yanuar.weather.data.repository.WeatherRepository
import id.yanuar.weather.mvp.DisposablePresenter
import id.yanuar.weather.presentation.di.scope.PerActivity
import id.yanuar.weather.util.OpenForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

@OpenForTesting
@PerActivity
class WeatherPresenter @Inject constructor(private val weatherRepository: WeatherRepository) :
        DisposablePresenter<WeatherView>() {

    fun getWeather(location: String) {
        disposable = view?.let { v ->
            weatherRepository.getWeather(location)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(v::showWeather) { v.showMessage(it?.message) }
        }
    }
}