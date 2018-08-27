package id.yanuar.weather.util

import id.yanuar.weather.data.remote.service.ApiService
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit


/**
 * Created by Yanuar Arifin
 * halo@yanuar.id
 */
abstract class ServiceTest {
    protected lateinit var mockWebServer: MockWebServer
    protected lateinit var retrofit: Retrofit

    @Before
    open fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.setDispatcher(ServerDispatcher())

        retrofit = ApiService.build(mockWebServer.url("/"))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}