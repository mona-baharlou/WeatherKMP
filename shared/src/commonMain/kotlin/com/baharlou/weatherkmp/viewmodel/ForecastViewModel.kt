package com.baharlou.weatherkmp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baharlou.weatherkmp.repository.CurWeatherDTO
import com.baharlou.weatherkmp.repository.ForecastDTO
import com.baharlou.weatherkmp.usecase.GetWeatherForecastUseCase
import com.baharlou.weatherkmp.viewmodel.model.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForecastViewModel(private val useCase: GetWeatherForecastUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

   // private val useCase = GetWeatherForecastUseCase()

    init {
        getWeatherForecasts()
    }

    fun getWeatherForecasts() {
        _uiState.update { UIState(loading = true) }
        viewModelScope.launch {
            useCase().fold(
                onSuccess = { result ->
                    _uiState.update { UIState(items = result.toModel()) }
                },
                onFailure = { throwable ->
                    _uiState.update { UIState(failed = throwable) }
                }
            )
        }
    }

    private fun Pair<CurWeatherDTO, ForecastDTO>.toModel(): List<WeatherModel> {
        val currentWeatherDTO = this.first
        val forecastDTO = this.second

        return mutableListOf<WeatherModel>().apply {
            add(
                WeatherModel.CurrentWeatherModel(
                    name = currentWeatherDTO.name,
                    icon = "https://openweathermap.org/img/wn/" + currentWeatherDTO.weather.getOrNull(0)?.icon + "@4x.png",
                    minMaxTemp = "${currentWeatherDTO.main.minTemp}°/${currentWeatherDTO.main.maxTemp}°",
                    temp = "${currentWeatherDTO.main.temp}°",
                    pressure = currentWeatherDTO.main.pressure.toString(),
                    humidity = currentWeatherDTO.main.humidity.toString(),
                    windSpeed = currentWeatherDTO.wind.speed.toString()
                )
            )
            add(WeatherModel.Divider)
            addAll(
                forecastDTO.forecasts.map { forecast ->
                    WeatherModel.ForecastModel(
                        icon = "https://openweathermap.org/img/wn/" + forecast.weather.getOrNull(0)?.icon + "@4x.png",
                        main = forecast.weather.getOrNull(0)?.main ?: "",
                        date = forecast.date,
                        minMaxTemp = "${forecast.main.minTemp.toInt()}°/${forecast.main.maxTemp.toInt()}°"
                    )
                }
            )
        }
    }

}

data class UIState(
    val items: List<WeatherModel> = listOf(),
    val loading: Boolean = false,
    val failed: Throwable? = null
)