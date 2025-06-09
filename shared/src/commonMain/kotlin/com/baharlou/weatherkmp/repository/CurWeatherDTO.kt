package com.baharlou.weatherkmp.repository

import kotlinx.serialization.Serializable

@Serializable
data class CurWeatherDTO (
    val name: String,
    val main: MainDTO,
    val weather: List<WeatherDTO>,
    val wind:WindDTO
)
