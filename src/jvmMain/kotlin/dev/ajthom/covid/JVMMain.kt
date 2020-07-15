package dev.ajthom.covid

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.responseObject
import com.github.kittinunf.result.Result
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration


@ImplicitReflectionSerializer
fun main() {
    val json = Json(JsonConfiguration.Stable.copy(isLenient = true, ignoreUnknownKeys = true, serializeSpecialFloatingPointValues = true, useArrayPolymorphism = true))
    val (request, response, result) = "https://covidtracking.com/api/v1/states/daily.json".httpGet().responseObject(loader = StateDailyData.serializer().list, json = json)
    when (result) {
        is Result.Success -> {
            val data = result.value
            val grouped = data.groupBy { it.state }
            val mn = (grouped["MN"] ?: emptyList()).sortedByDescending { it.date }
            mn.forEach {
                println(it)
            }
        }
        is Result.Failure -> {
            result.error.printStackTrace()
        }
    }
}
