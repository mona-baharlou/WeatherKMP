package com.baharlou.weatherkmp.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherRepository(private val httpClient: HttpClient) : IWeatherRepository {

    //private val httpClient = Network.httpClient

    override suspend fun getCurWeather(): Result<CurWeatherDTO> {

        return try {
            val response = httpClient.get("weather").body<CurWeatherDTO>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getForecast(): Result<ForecastDTO> {
        return try {
            val response = httpClient.get("forecast").body<ForecastDTO>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}