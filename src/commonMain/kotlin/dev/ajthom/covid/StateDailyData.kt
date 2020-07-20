package dev.ajthom.covid

import com.soywiz.klock.Date
import kotlinx.serialization.Serializable

expect fun formatDate(date: Date): String

@Serializable
data class StateDailyData(
    val date: String,
    val state: String,
    val positive: Int?,
    val negative: Int?,
    val pending: Int?,
    val hospitalizedCurrently: Int?,
    val hospitalizedCumulative: Int?,
    val inIcuCurrently: Int?,
    val inIcuCumulative: Int?,
    val onVentilatorCurrently: Int?,
    val onVentilatorCumulative: Int?,
    val recovered: Int?,
    val dataQualityGrade: String?,
    val lastUpdateEt: String?,
    val dateModified: String?,
    val checkTimeEt: String?,
    val death: Int?,
    val hospitalized: Int?,
    val dateChecked: String?,
    val totalTestsViral: Int?,
    val positiveTestsViral: Int?,
    val negativeTestsViral: Int?,
    val positiveCasesViral: Int?,
    val deathConfirmed: Int?,
    val deathProbable: Int?,
    val fips: String,
    val positiveIncrease: Int,
    val negativeIncrease: Int,
    val total: Int,
    val totalTestResults: Int,
    val totalTestResultsIncrease: Int,
    val posNeg: Int,
    val deathIncrease: Int,
    val hospitalizedIncrease: Int?,
    val hash: String,
    val commercialScore: Int,
    val negativeRegularScore: Int,
    val negativeScore: Int,
    val positiveScore: Int,
    val score: Int,
    val grade: String,
    var previousDay: StateDailyData? = null,
    var nextDay: StateDailyData? = null
) {
    val dataDate: Date
        get() {
            val year = date.substring(0, 4).toInt()
            val month = date.substring(4, 6).toInt()
            val day = date.substring(6, 8).toInt()
            return Date(year, month, day)
        }

    fun formattedDate(): String {
        val d = dataDate
        return formatDate(d)
    }
}

data class StateData(val state: String, val dailyData: List<StateDailyData>)
