package id.yanuar.weather.data.remote.service

import id.yanuar.weather.data.remote.response.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
interface WeatherService {
    @GET("forecast.json")
    fun getCurrentWeather(@Query("q") q: String,
                          @Query("days") days: Int): Single<Weather>
}