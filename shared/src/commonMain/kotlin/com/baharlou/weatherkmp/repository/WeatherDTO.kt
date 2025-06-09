package com.baharlou.weatherkmp.repository

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String

)