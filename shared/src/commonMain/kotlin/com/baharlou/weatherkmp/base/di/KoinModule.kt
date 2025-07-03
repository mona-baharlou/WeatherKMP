package com.baharlou.weatherkmp.base.di
import com.baharlou.weatherkmp.BuildKonfig
import com.baharlou.weatherkmp.repository.IWeatherRepository
import com.baharlou.weatherkmp.repository.WeatherRepository
import com.baharlou.weatherkmp.usecase.GetWeatherForecastUseCase
import com.baharlou.weatherkmp.viewmodel.ForecastViewModel
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val coreModule = module{

    single {
        val httpClient = HttpClient {
            expectSuccess = true

            defaultRequest {
                url("https://api.openweathermap.org/data/2.5/")
                url {
                    parameters.append("appid", BuildKonfig.API_KEY)
                    parameters.append("lat", "35.6892523")
                    parameters.append("lon", "51.3896004")
                    parameters.append("units", "metric")
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(message)
                    }

                }

                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                    coerceInputValues = true

                })

            }
        }

    }

}

val repositoryModule = module {
    single<IWeatherRepository> { WeatherRepository(get()) }
}

val usecaseModule = module{

    factory { GetWeatherForecastUseCase(get()) }
}

val viewModelModule = module {
  SharedViewModel { ForecastViewModel(get()) }
}
