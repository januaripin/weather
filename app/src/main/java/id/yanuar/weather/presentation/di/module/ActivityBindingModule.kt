package id.yanuar.weather.presentation.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.yanuar.weather.presentation.weather.WeatherActivity
import id.yanuar.weather.presentation.di.scope.PerActivity

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun weatherActivityInjector(): WeatherActivity
}