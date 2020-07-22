package dev.ajthom.covid

import kotlinx.serialization.ImplicitReflectionSerializer

@ImplicitReflectionSerializer
fun main() {
    val stateNames = StateInfos()
    val fetcher = DailyDataFetcher()
    fetcher.getDailyData { states ->
        states.filter { it.state == "MN" }.forEach { state ->
            state.dailyData.forEach { daily ->
                println("${stateNames.getName(daily.getState())} – ${daily.formattedDate()}: ${daily.getPositive()}")
            }
        }
    }
    fetcher.getNationalDailyData { data ->
        data.dailyData.forEach { daily ->
            println("${daily.formattedDate()}: ${daily.getPositive()}")
        }
    }
}
