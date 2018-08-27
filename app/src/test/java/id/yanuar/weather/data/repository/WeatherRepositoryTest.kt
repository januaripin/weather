package id.yanuar.weather.data.repository

import id.yanuar.weather.data.remote.response.Weather
import id.yanuar.weather.data.remote.service.WeatherService
import id.yanuar.weather.DataUtils
import id.yanuar.weather.mock
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
class WeatherRepositoryTest {
    private var weatherService = mock<WeatherService>()

    lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        weatherRepository = WeatherRepository(weatherService)
    }

    @Test
    fun testGetWeatherEmitsValue() {
        val weather = DataUtils.createWeather()

        whenever(weatherService.getCurrentWeather(anyString(), anyInt()))
                .thenReturn(Single.just(weather))

        val testObserver = TestObserver<Weather>()

        weatherRepository.getWeather("-7.3893177,109.3610728").subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue(weather)
    }

    @Test
    fun testGetWeatherCallApi() {
        val weather = DataUtils.createWeather()

        whenever(weatherService.getCurrentWeather(anyString(), anyInt()))
                .thenReturn(Single.just(weather))

        weatherRepository.getWeather("-7.3893177,109.3610728").subscribe()

        verify(weatherService).getCurrentWeather("-7.3893177,109.3610728", 6)
    }
}