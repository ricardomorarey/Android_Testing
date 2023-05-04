package com.cursosandroidant.forecastweather.common.dataAccess

import com.cursosandroidant.forecastweather.entities.WeatherForecastEntity
import com.google.gson.Gson
import java.io.InputStreamReader

class JSONFileLoader {

    private var jsonstr: String? =  null

    fun loaderJsonStriung(file: String): String? {
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        jsonstr = loader.readText()
        loader.close()
        return  jsonstr
    }

    fun loadWeatherForecastEntity(file: String): WeatherForecastEntity?{
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(file))
        jsonstr = loader.readText()
        loader.close()
        return Gson().fromJson(jsonstr, WeatherForecastEntity::class.java)
    }
}