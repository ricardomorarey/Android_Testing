package com.cursosandroidant.forecastweather.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cursosandroidant.forecastweather.MainCoroutineRule
import com.cursosandroidant.forecastweather.common.dataAccess.JSONFileLoader
import com.cursosandroidant.forecastweather.common.dataAccess.WeatherForecastService
import com.cursosandroidant.forecastweather.entities.WeatherForecastEntity
import com.cursosandroidant.historicalweatherref.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainViewModelTest{

    @get: Rule
    val instantExecuterRule = InstantTaskExecutorRule()
    @get: Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private lateinit var service: WeatherForecastService

    companion object{
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setUpCommon(){
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setUp(){
        viewModel = MainViewModel()
        service = retrofit.create(WeatherForecastService::class.java)
    }

    @Test
    fun checkWeatherIsNonullTest(){
        runBlocking {
            val result = service.getWeatherForecastByCoordinates(19.4342, -99.1962,
                "6364546cb00c113bff0065ac8aea2438", "metric", "en")
            assertThat(result.current, `is`(notNullValue()))
        }
    }

    @Test
    fun checkTimeZonereturnsMexicoTest(){
        runBlocking {
            val result = service.getWeatherForecastByCoordinates(19.4342, -99.1962,
                "6364546cb00c113bff0065ac8aea2438", "metric", "en")
            assertThat(result.timezone, `is`("America/Mexico_City"))
        }
    }

    @Test
    fun checkErrorResponseWhitOnlyCoordinatesTest(){
        runBlocking {
            try {
                val result = service.getWeatherForecastByCoordinates(19.4342, -99.1962,
                    "", "", "")
            } catch (e: Exception) {
                assertThat(e.localizedMessage,  `is`("HTTP 401 Unauthorized"))
            }

        }
    }

    @Test
    fun checkhourlySizeTes(){
        runBlocking {
                viewModel.getWeatherAndForecast(19.4342, -99.1962,
                    "6364546cb00c113bff0065ac8aea2438", "metric", "en")
                val result = viewModel.getResult().getOrAwaitValue()
                assertThat(result.hourly.size, `is`(48))
        }
    }

    @Test
    fun compareHourlyRemoteWithLocalSize(){
        runBlocking {
            val remoteResult = service.getWeatherForecastByCoordinates(19.4342, -99.1962,
                "6364546cb00c113bff0065ac8aea2438", "metric", "en")

            val localResult = JSONFileLoader().loadWeatherForecastEntity("weather_forecast_response_ssuccess.json")

            assertThat(localResult?.hourly?.size, `is`(remoteResult.hourly.size))
            assertThat(localResult?.timezone, `is`(remoteResult.timezone))
        }
    }
}