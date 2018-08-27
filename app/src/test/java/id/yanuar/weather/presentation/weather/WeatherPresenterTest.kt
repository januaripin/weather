package id.yanuar.weather.presentation.weather

import id.yanuar.weather.data.repository.WeatherRepository
import id.yanuar.weather.DataUtils
import id.yanuar.weather.util.RxImmediateSchedulerRule
import id.yanuar.weather.mock
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
class WeatherPresenterTest {
    @JvmField
    @Rule
    var immediateSchedulerRule = RxImmediateSchedulerRule()

    private var weatherRepository = mock<WeatherRepository>()

    private var weatherView = mock<WeatherView>()

    lateinit var weatherPresenter: WeatherPresenter

    @Before
    fun setUp() {
        weatherPresenter = WeatherPresenter(weatherRepository)
        weatherPresenter.attachView(weatherView)
    }

    @After
    fun tearDown() {
        weatherPresenter.detachView()
    }

    @Test
    fun getWeatherReturnWeather() {
        val weather = DataUtils.createWeather()

        whenever(weatherRepository.getWeather(anyString()))
                .thenReturn(Single.just(weather))

        weatherPresenter.getWeather("-7.3893177,109.3610728")

        verify(weatherView).showWeather(weather)
        verify(weatherView, never()).showMessage("")
    }

    @Test
    fun getWeatherFails() {
        whenever(weatherRepository.getWeather(anyString()))
                .thenReturn(Single.error(Throwable("Failed")))

        weatherPresenter.getWeather("-7.3893177,109.3610728")

        verify(weatherView).showMessage("Failed")
        verify(weatherView, never()).showWeather(DataUtils.createWeather())
    }
}