package dev.ajthom.covid

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.soywiz.klock.Date
import com.soywiz.klock.KlockLocale

actual fun formatDate(date: Date): String {
    return date.format(KlockLocale.default.formatDateLong)
}

/**
 * Will freeze and return the item in environments that use freezing. Otherwise it will just return the item
 */
actual fun <T> T.doFreeze(): T {
    return this
}

actual fun loadFromUrl(url: String, callback: (String) -> Unit) {
    val (_, _, result) = "https://covidtracking.com/api/v1/states/daily.json".httpGet().responseString()
    when (result) {
        is Result.Success -> {
            callback(result.value)
        }
        is Result.Failure -> {
            result.error.printStackTrace()
        }
    }
}
