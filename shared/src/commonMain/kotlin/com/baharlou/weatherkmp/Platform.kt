package com.baharlou.weatherkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform