package com.cursosandroidant.forecastweather.common.dataAccess

import com.cursosandroidant.forecastweather.entities.WeatherForecastEntity
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class ResponseServerTest {

    private lateinit var mokitoWebServer: MockWebServer

    @Before
    fun setup() {
        mokitoWebServer = MockWebServer()
        mokitoWebServer.start()
    }

    @After
    fun tearDown() {
        mokitoWebServer.shutdown()
    }

    @Test
    fun readResponseFileSucces() {
        val reader = JSONFileLoader().loaderJsonStriung("weather_forecast_response_ssuccess.json")
        assertThat(reader, `is`(notNullValue()))
        assertThat(reader, containsString("America/Mexico_City"))
    }

    @Test
    fun getWheatherForecastTimeZoneExist() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(
                JSONFileLoader().loaderJsonStriung("weather_forecast_response_ssuccess.json")
                    ?: "{errorCode:34}"
            )
        mokitoWebServer.enqueue(response)

        assertThat(response.getBody()?.readUtf8(), containsString("\"timezone\""))
    }

    @Test
    fun getWheatherForecastResponseFail() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(
                JSONFileLoader().loaderJsonStriung("weather_forecast_response_error")
                    ?: "{errorCode:34}"
            )
        mokitoWebServer.enqueue(response)

        assertThat(response.getBody()?.readUtf8(), containsString("{\"cod\": 401, \"message\": \"Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.\"}"))
    }

    @Test
    fun `get hte wheatherForecast and check if hourly list is empty`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(
                JSONFileLoader().loaderJsonStriung("weather_forecast_response_ssuccess.json")
                    ?: "{errorCode:34}"
            )
        mokitoWebServer.enqueue(response)

        assertThat(response.getBody()?.readUtf8(), containsString("hourly"))
        val json = Gson().fromJson(response.getBody()?.readUtf8() ?: "", WeatherForecastEntity::class.java)
        assertThat(json.hourly.isEmpty(), `is`(false))
    }
}