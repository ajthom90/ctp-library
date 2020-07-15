package dev.ajthom.covid

import com.soywiz.klock.Date
import com.soywiz.klock.KlockLocale
import org.w3c.xhr.XMLHttpRequest

actual fun <T> T.doFreeze() = this

actual fun loadFromUrl(url: String, callback: (String) -> Unit) {
    val xhr = XMLHttpRequest()
    xhr.open("GET", "")
    xhr.send()
    xhr.onreadystatechange = { _ ->
        if (xhr.readyState == 4.toShort() && xhr.status == 200.toShort()) {
            callback(xhr.responseText)
        }
    }
}

actual fun formatDate(date: Date): String {
    return date.format(KlockLocale.default.formatDateFull)
}
