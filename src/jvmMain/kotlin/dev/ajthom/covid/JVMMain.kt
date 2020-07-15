package dev.ajthom.covid

import kotlinx.serialization.ImplicitReflectionSerializer

@ImplicitReflectionSerializer
fun main() {
    val stateNames = StateNames()
    val fetcher = DailyDataFetcher()
    fetcher.getDailyData { states ->
        states.filter { it.state == "MN" }.forEach { state ->
            state.dailyData.forEach { daily ->
                println("${stateNames.getName(daily.state)} – ${daily.formattedDate()}: ${daily.positive ?: 0}")
            }
        }
    }
}
