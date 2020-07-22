package dev.ajthom.covid

import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class DailyDataFetcher {
    fun getDailyData(callback: (List<StateData>) -> Unit) {
        loadFromUrl("https://covidtracking.com/api/v1/states/daily.json") { dataStr ->
            val stateInfo = StateInfos()
            val json = Json(
                JsonConfiguration.Stable.copy(
                    ignoreUnknownKeys = true,
                    useArrayPolymorphism = true,
                    isLenient = true
                )
            )
            val parsed = json.parse(StateDailyData.serializer().list, dataStr).sortedByDescending { it.date }
                .groupBy { it.getState().orEmpty() }
            val states = parsed.keys.map {
                StateData(state = it, dailyData = emptyList())
            }
            val statesWithDailies = states.map {
                val dailies = parsed[it.state] ?: emptyList()
                dailies.doNextAndPrevious()
                val copy = it.copy(dailyData = dailies.doFreeze())
                copy.doFreeze()
            }.sortedBy {
                stateInfo.getName(it.state)
            }
            callback(statesWithDailies.doFreeze())
        }
    }

    private fun List<StateDailyData>.doNextAndPrevious(): List<StateDailyData> {
        withIndex().forEach { (index, data) ->
            val nextDay = if (index != 0) {
                this[index - 1]
            } else {
                null
            }
            val previousDay = if (index != this.size - 1) {
                this[index + 1]
            } else {
                null
            }
            data.also {
                it.previousDay = previousDay
                it.nextDay = nextDay
            }
        }
        return this
    }

    fun getNationalDailyData(callback: (StateData) -> Unit) {
        loadFromUrl("https://covidtracking.com/api/v1/us/daily.json") { dataStr ->
            val json = Json(JsonConfiguration.Stable.copy(ignoreUnknownKeys = true, useArrayPolymorphism = true, isLenient = true))
            val parsed = json.parse(StateDailyData.serializer().list, dataStr).sortedByDescending { it.date }
            val stateData = StateData(state = "US", dailyData = parsed.doNextAndPrevious().doFreeze())
            callback(stateData.doFreeze())
        }
    }
}

/**
 * Will freeze and return the item in environments that use freezing. Otherwise it will just return the item
 */
expect fun <T> T.doFreeze(): T

expect fun loadFromUrl(url: String, callback: (String) -> Unit)
