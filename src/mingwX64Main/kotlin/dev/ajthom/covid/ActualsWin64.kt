package dev.ajthom.covid

import com.soywiz.klock.Date
import com.soywiz.klock.KlockLocale
import kotlin.native.concurrent.freeze

actual fun <T> T.doFreeze() = this.freeze()

actual fun loadFromUrl(url: String, callback: (String) -> Unit) {
    callback("")
}

actual fun formatDate(date: Date): String {
    return date.format(KlockLocale.default.formatDateLong)
}
