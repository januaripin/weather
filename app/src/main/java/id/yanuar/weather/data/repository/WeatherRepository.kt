package id.yanuar.weather.data.repository

import id.yanuar.weather.data.remote.service.WeatherService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

@Singleton
class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {
    fun getWeather(location: String) = weatherService.getCurrentWeather(location, 6)
}