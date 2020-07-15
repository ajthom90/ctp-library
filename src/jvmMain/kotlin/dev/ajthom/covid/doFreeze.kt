package dev.ajthom.covid

/**
 * Will freeze and return the item in environments that use freezing. Otherwise it will just return the item
 */
actual fun <T> T.doFreeze(): T {
    return this
}

actual fun loadFromUrl(url: String, callback: (String) -> Unit) {
}
