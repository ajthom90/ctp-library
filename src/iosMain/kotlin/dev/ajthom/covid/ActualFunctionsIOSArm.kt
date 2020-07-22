package dev.ajthom.covid

import com.soywiz.klock.Date
import platform.Foundation.*
import kotlin.native.concurrent.freeze

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

actual fun formatDate(date: Date): String {
    val year = date.year
    val month = date.month1
    val day = date.day
    val dateComponents = NSDateComponents()
    dateComponents.year = year.toLong()
    dateComponents.day = day.toLong()
    dateComponents.month = month.toLong()
    val formatter = NSDateFormatter()
    formatter.dateStyle = NSDateFormatterLongStyle
    formatter.locale = NSLocale.currentLocale()
    val nsDate = NSCalendar.currentCalendar.dateFromComponents(dateComponents)!!
    return formatter.stringFromDate(nsDate)
}
