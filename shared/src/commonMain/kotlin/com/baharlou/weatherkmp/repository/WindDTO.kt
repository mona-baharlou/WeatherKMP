package com.baharlou.weatherkmp.repository

import kotlinx.serialization.Serializable

@Serializable
data class WindDTO (
    val speed: Float,
    val deg: Int,
    val gust: Float
)