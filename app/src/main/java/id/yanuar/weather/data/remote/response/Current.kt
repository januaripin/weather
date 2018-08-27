package id.yanuar.weather.data.remote.response

import com.google.gson.annotations.SerializedName


class Current(
        val lastUpdated: String,

        @SerializedName(value = "temp_c")
        val temp: Double,

        val isDay: Int,

        val condition: Condition
)