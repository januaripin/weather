package id.yanuar.weather.data.remote.response

class Weather(
        val location: WeatherLocation,
        val current: Current,
        val forecast: Forecast
)