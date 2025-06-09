package com.baharlou.weatherkmp.usecase

import com.baharlou.weatherkmp.repository.CurWeatherDTO
import com.baharlou.weatherkmp.repository.ForecastDTO
import com.baharlou.weatherkmp.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class GetWeatherForecastUseCase {
    private val repository = WeatherRepository()

    suspend operator fun invoke(dispatcher: CoroutineDispatcher = Dispatchers.IO): Result<Pair<CurWeatherDTO, ForecastDTO>> {

        return coroutineScope {
            try {
                withContext(dispatcher) {
                    val curWeatherAsync = async { repository.getCurWeather() }
                    val forecastAsync = async { repository.getForecast() }
                    Result.success(
                        Pair(
                            curWeatherAsync.await().getOrThrow(),
                            forecastAsync.await().getOrThrow()
                        )
                    )

                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}