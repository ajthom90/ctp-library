package dev.ajthom.covid

fun main() {
    val fetcher = DailyDataFetcher()
    val stateNames = StateNames()
    fetcher.getDailyData { states ->
        states.forEach { state ->
            state.dailyData.forEach { daily ->
                println("${stateNames.getName(state.state)}: ${daily.getPositive()}")
            }
        }
    }
}
