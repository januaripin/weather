package id.yanuar.weather.presentation.weather

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import id.yanuar.weather.R
import id.yanuar.weather.WeatherApp
import id.yanuar.weather.fakeActivityInjector
import id.yanuar.weather.mock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever


/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

@RunWith(AndroidJUnit4::class)
class WeatherActivityTest {

    val mockPresenter = mock<WeatherPresenter>()

    @Rule
    @JvmField
    var activityRule = object : ActivityTestRule<WeatherActivity>(WeatherActivity::class.java, true, true) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            val app = InstrumentationRegistry.getTargetContext().applicationContext as WeatherApp
            app.activityInjector = fakeActivityInjector<WeatherActivity> {
                presenter = mockPresenter
            }
        }
    }

    @Rule
    @JvmField
    var grantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    @Test
    fun launchActivityThenShowProgressBar() {
        onView(withId(R.id.pbLayout)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun launchActivityThenAttachViewToPresenter() {
        verify(mockPresenter).attachView(activityRule.activity)
    }

    @Test
    fun finishActivityThenDetachViewFromPresenter() {
        activityRule.finishActivity()
        verify(mockPresenter).detachView()
    }
}