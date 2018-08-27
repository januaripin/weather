package id.yanuar.weather.data.remote.response

import com.google.gson.annotations.SerializedName

class Forecast(
        @SerializedName(value = "forecastday")
        val forecastDays: List<ForecastDay>
)