package com.baharlou.weatherkmp.repository

interface IWeatherRepository {
    suspend fun getCurWeather(): Result<CurWeatherDTO>
    suspend fun getForecast(): Result<ForecastDTO>
}