package id.yanuar.weather

import id.yanuar.weather.data.remote.response.*

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
object DataUtils {

    fun createWeather() = Weather(createLocation(), createCurrent(), createForecast())

    fun createCurrent() = Current("", 0.0, 1, createCondition())

    fun createForecast() = Forecast(createForecastDays(createDay(createCondition())))

    fun createForecastDays(day: Day) = mutableListOf(ForecastDay("", day))

    fun createDay(condition: Condition) = Day(0.0, 0.0, 0.0, condition)

    fun createCondition() = Condition("cerah", "icon", 1)

    fun createLocation() = WeatherLocation("Purbalingga Kidul", "Jawa Tengah", "Indonesia")
}