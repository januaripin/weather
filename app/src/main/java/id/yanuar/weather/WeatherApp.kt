package id.yanuar.weather

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import id.yanuar.weather.presentation.di.component.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject


/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
class WeatherApp : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        when {
            BuildConfig.DEBUG -> Timber.plant(DebugTree())
        }
        DaggerAppComponent.builder().create(this).inject(this)
    }

    override fun activityInjector() = activityInjector
}