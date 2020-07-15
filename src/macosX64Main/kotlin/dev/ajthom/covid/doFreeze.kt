package dev.ajthom.covid

import platform.Foundation.*
import kotlin.native.concurrent.freeze

/**
 * Will freeze and return the item in environments that use freezing. Otherwise it will just return the item
 */
actual fun <T> T.doFreeze(): T {
    return this.freeze()
}

actual fun loadFromUrl(url: String, callback: (String) -> Unit) {
    val u = NSURL(string = url)
    val completionHandler: (NSData?, NSURLResponse?, NSError?) -> Unit = { data, _, _ ->
        val dataStr = NSString.create(data!!, NSUTF8StringEncoding) as String
        callback(dataStr.doFreeze())
    }
    completionHandler.doFreeze()
    NSURLSession.sharedSession.dataTaskWithURL(u, completionHandler).resume()
}
