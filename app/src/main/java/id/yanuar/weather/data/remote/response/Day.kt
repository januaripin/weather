package id.yanuar.weather.data.remote.response

import com.google.gson.annotations.SerializedName

class Day(
        @SerializedName(value = "maxtemp_c")
        val maxTemp: Double,

        @SerializedName(value = "mintemp_c")
        val minTemp: Double,

        @SerializedName(value = "avgtemp_c")
        val avgTemp: Double,

        val condition: Condition)