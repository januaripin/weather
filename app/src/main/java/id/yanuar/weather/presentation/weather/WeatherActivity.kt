package id.yanuar.weather.presentation.weather

import android.Manifest
import android.app.Activity
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.view.View.GONE
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import id.yanuar.weather.R
import id.yanuar.weather.data.remote.response.Weather
import id.yanuar.weather.util.SnackbarUtils
import kotlinx.android.synthetic.main.activity_weather.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), WeatherView, LocationListener {

    @Inject
    lateinit var presenter: WeatherPresenter

    private val adapter = WeatherAdapter()

    private var permissionDenied = true

    private val perms = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION)

    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        presenter.attachView(this)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        tvProvidedBy.movementMethod = LinkMovementMethod.getInstance()

        rvForecast.setHasFixedSize(true)
        rvForecast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvForecast.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onResume() {
        super.onResume()
        getWeather()
    }

    override fun showMessage(message: String?, callback: Snackbar.Callback?) {
        SnackbarUtils.create(viewRoot, message, callback)
    }

    override fun showWeather(weather: Weather) {
        Timber.d(weather.toString())
        with(weather) {
            val forecasts = forecast.forecastDays

            runOnUiThread {
                Picasso.get()
                        .load("https:${current.condition.icon}")
                        .into(imageCondition, object : Callback {
                            override fun onSuccess() {
                                pbLayout.visibility = GONE
                            }

                            override fun onError(e: Exception?) {
                                showMessage(e?.message)
                            }
                        })

                tvTemperature.text = "${current.temp}°"
                tvLocationName.text = location.name

                rvForecast.adapter = adapter
                adapter.items = forecasts.subList(1, forecasts.size).toMutableList()
            }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            presenter.getWeather("${it.latitude},${it.longitude}")
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        getLocation()
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    @AfterPermissionGranted(12345)
    private fun enablePermissions() {
        if (EasyPermissions.hasPermissions(this, *perms)) {
            permissionDenied = false
            getWeather()
        } else {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        EasyPermissions.requestPermissions(
                this,
                "GPS permission is needed to take your current location.",
                12345,
                *perms
        )
    }

    private fun getWeather() {
        if (permissionDenied) {
            enablePermissions()
        } else {
            when {
                isGooglePlayServicesAvailable(this) -> getLocationUsingPlayServices()
                else -> getLocation()
            }
        }
    }

    @Throws(SecurityException::class)
    private fun getLocationUsingPlayServices() {
        LocationServices.getFusedLocationProviderClient(this)
                .lastLocation.addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.run {
                    presenter.getWeather("$latitude,$longitude")
                } ?: run {
                    getLocation()
                }
            } else {
                getLocation()
            }
        }
    }

    @Throws(SecurityException::class)
    private fun getLocation() {
        val provider = when {
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> {
                LocationManager.GPS_PROVIDER
            }
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> {
                LocationManager.NETWORK_PROVIDER
            }
            else -> null
        }

        provider?.let {
            locationManager.requestSingleUpdate(it, this, null)
        }
    }

    private fun isGooglePlayServicesAvailable(activity: Activity): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(activity)
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show()
            }
            return false
        }
        return true
    }
}
