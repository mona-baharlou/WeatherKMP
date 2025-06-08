package com.baharlou.weatherkmp.base

import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

object Network  {

    val httpClient = HttpClient{
        exceptSuccess = true

        defaultRequest {
            url("https://api.openweathermap.org/data/2.5")
            url{
                parameters.append("appid","50a4affe1da8577cce64acffaa91bdb6")
            }
        }

        install(Logging){
            logger = object: Logger{
                override fun log(message: String) {
                    TODO("Not yet implemented")
                    //Nappier.d(message)
                }

            }

            level = LogLevel.ALL
        }

        install(ContentNegotiation){
            json(Json{
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
                coerceInputValues = true

            })

        }
    }
}