package com.baharlou.weatherkmp.repository

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainDTO (
    val temp: Float,
    @SerialName("temp_main")
    val mainTemp: Float,
    @SerialName("temp_max")
    val maxTemp: Float,
    val pressure:Int,
    val humidity: Int
)