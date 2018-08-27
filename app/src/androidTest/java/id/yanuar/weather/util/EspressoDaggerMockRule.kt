package id.yanuar.weather.util

import android.support.test.InstrumentationRegistry
import id.yanuar.weather.WeatherApp
import id.yanuar.weather.presentation.di.component.AppComponent
import id.yanuar.weather.presentation.di.module.AppModule
import it.cosenonjaviste.daggermock.DaggerMock

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

fun espressoDaggerMockRule() = DaggerMock.rule<AppComponent>(AppModule(app)) {
    set { component -> component.inject(app) }
    customizeBuilder<AppComponent.Builder> { it }
}

val app: WeatherApp get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as WeatherApp