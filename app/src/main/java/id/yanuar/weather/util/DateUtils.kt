package id.yanuar.weather.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
object DateUtils {
    @SuppressLint("SimpleDateFormat")
    fun getDay(str: String): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.time = formatter.parse(str)

        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)
    }
}