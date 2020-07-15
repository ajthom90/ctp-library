package dev.ajthom.covid

import com.soywiz.klock.Date
import platform.Foundation.*

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
