package dev.ajthom.covid

import com.soywiz.klock.Date
import kotlinx.serialization.Serializable

expect fun formatDate(date: Date): String

@Serializable
data class StateDailyData(
    val date: String,
    private val state: String? = null,
    private val states: Int? = null,
    private val positive: Int?,
    private val negative: Int?,
    private val pending: Int?,
    private val hospitalizedCurrently: Int?,
    private val hospitalizedCumulative: Int?,
    private val inIcuCurrently: Int?,
    private val inIcuCumulative: Int?,
    private val onVentilatorCurrently: Int?,
    private val onVentilatorCumulative: Int?,
    private val recovered: Int?,
    private val dataQualityGrade: String? = null,
    private val lastUpdateEt: String? = null,
    private val dateModified: String? = null,
    private val checkTimeEt: String? = null,
    private val death: Int?,
    private val hospitalized: Int?,
    private val dateChecked: String?,
    private val totalTestsViral: Int? = null,
    private val positiveTestsViral: Int? = null,
    private val negativeTestsViral: Int? = null,
    private val positiveCasesViral: Int? = null,
    private val deathConfirmed: Int? = null,
    private val deathProbable: Int? = null,
    private val fips: String? = null,
    val positiveIncrease: Int,
    val negativeIncrease: Int,
    val total: Int,
    val totalTestResults: Int,
    val totalTestResultsIncrease: Int,
    val posNeg: Int,
    val deathIncrease: Int,
    val hospitalizedIncrease: Int?,
    val hash: String,
    val commercialScore: Int? = null,
    val negativeRegularScore: Int? = null,
    val negativeScore: Int? = null,
    val positiveScore: Int? = null,
    val score: Int? = null,
    val grade: String? = null,
    var previousDay: StateDailyData? = null,
    var nextDay: StateDailyData? = null
) {
    fun activeCases(): Long {
        return positive.orZero() - death.orZero() - recovered.orZero()
    }

    fun getHospitalizedCurrently() = hospitalizedCurrently.orZero()
    fun getHospitalizedCumulative() = hospitalizedCumulative.orZero()
    fun getState() = state ?: "US"
    fun getPositive() = positive.orZero()
    fun getRecovered() = recovered.orZero()
    fun getDeaths() = death.orZero()

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

fun Int?.orZero(): Long {
    return this?.toLong() ?: 0L
}

fun Long?.orZero(): Long {
    return this ?: 0L
}
