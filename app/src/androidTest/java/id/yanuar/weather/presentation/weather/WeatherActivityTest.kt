package id.yanuar.weather.presentation.weather

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import id.yanuar.weather.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when` as whenever

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */

@RunWith(AndroidJUnit4::class)
class WeatherActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(WeatherActivity::class.java, false, false)

    @Rule
    @JvmField
    var grantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    @Test
    fun testStartActivity() {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().uiAutomation.executeShellCommand(
                    "pm grant " + getTargetContext().packageName
                            + " android.permission.ACCESS_FINE_LOCATION")
        }*/

        activityRule.launchActivity(null)
        onView(withId(R.id.pbLayout)).check(ViewAssertions.matches(isDisplayed()))
    }
}