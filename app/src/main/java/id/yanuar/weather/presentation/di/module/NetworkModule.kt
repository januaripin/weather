package id.yanuar.weather.presentation.di.module

import dagger.Module
import dagger.Provides
import id.yanuar.weather.data.remote.service.ApiService
import id.yanuar.weather.data.remote.service.WeatherService
import okhttp3.HttpUrl
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit() = ApiService.build(HttpUrl.parse("https://api.apixu.com/v1/"))

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit) = retrofit.create(WeatherService::class.java)
}