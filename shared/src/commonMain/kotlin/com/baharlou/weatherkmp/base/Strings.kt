package com.baharlou.weatherkmp.base

import com.baharlou.weather.MR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc


fun getTemp() = StringDesc.Resource(MR.strings.temp)
fun getWind() = StringDesc.Resource(MR.strings.wind)
fun getPressure() = StringDesc.Resource(MR.strings.pressure)
fun getHumidity() = StringDesc.Resource(MR.strings.humidity)
fun getForecast() = StringDesc.Resource(MR.strings.forecast_divider)
fun getPercentage(percentage: String) = StringDesc.ResourceFormatted(MR.strings.percentage, percentage)
