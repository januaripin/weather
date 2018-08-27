package id.yanuar.weather.presentation.weather

import id.yanuar.weather.data.remote.response.Weather
import id.yanuar.weather.mvp.MvpView

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
interface WeatherView: MvpView {
    fun showWeather(weather: Weather)
}