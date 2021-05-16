package com.example.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random


/// WebServices ///
class ForecastRepository {

    //// Weekly forecast /// Mutable
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipCode: String){
        val randomValues = List(12){ Random.nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map {temp ->
            DailyForecast(temp,getTempDescription(temp))
        }
        _weeklyForecast.setValue(forecastItems)
    }
    private fun getTempDescription(temp: Float): String{

        return when (temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Any thing Below O does not make sense"
            in 0f.rangeTo(32F) -> "Way too cold"
            in 32f.rangeTo(55f) -> "Slightly Cold"
            in 55f.rangeTo(70f) -> "The weather is good"
            in 70f.rangeTo(100f) -> "The weather is too Hot"
            else -> "Doesnot Compute"

        }
    }
}