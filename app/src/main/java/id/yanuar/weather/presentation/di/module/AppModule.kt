package id.yanuar.weather.presentation.di.module

import dagger.Module
import dagger.android.AndroidInjectionModule
import id.yanuar.weather.WeatherApp

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

@Module(includes = [AndroidInjectionModule::class, ActivityBindingModule::class])
class AppModule(private val app: WeatherApp)