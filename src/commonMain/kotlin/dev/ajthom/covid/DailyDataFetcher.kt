package dev.ajthom.covid

import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class DailyDataFetcher {
    fun getDailyData(callback: (List<StateData>) -> Unit) {
        loadFromUrl("https://covidtracking.com/api/v1/states/daily.json") { dataStr ->
            val json = Json(
                JsonConfiguration.Stable.copy(
                    ignoreUnknownKeys = true,
                    useArrayPolymorphism = true,
                    isLenient = true
                )
            )
            val parsed = json.parse(StateDailyData.serializer().list, dataStr).sortedByDescending { it.date }
                .groupBy { it.state }
            val states = parsed.keys.map {
                StateData(state = it, dailyData = emptyList())
            }
            val statesWithDailies = states.map {
                val dailies = parsed[it.state] ?: emptyList()
                dailies.doFreeze()
                val copy = it.copy(dailyData = dailies)
                copy.doFreeze()
            }
            callback(statesWithDailies.doFreeze())
        }
    }
}

/**
 * Will freeze and return the item in environments that use freezing. Otherwise it will just return the item
 */
expect fun <T> T.doFreeze(): T

expect fun loadFromUrl(url: String, callback: (String) -> Unit)
