package id.yanuar.weather.presentation.di.component

import dagger.Component
import dagger.android.AndroidInjector
import id.yanuar.weather.WeatherApp
import id.yanuar.weather.presentation.di.module.AppModule
import id.yanuar.weather.presentation.di.module.NetworkModule
import javax.inject.Singleton

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent : AndroidInjector<WeatherApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WeatherApp>()
}