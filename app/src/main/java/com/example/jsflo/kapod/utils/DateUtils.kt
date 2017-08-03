package com.example.jsflo.kapod.utils

import java.text.SimpleDateFormat
import java.util.*

// Creates an SDF object on every call but it works for small demo
fun Date.toPrettyFormat(): String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(this)
fun Date.toJsonRequestFormat(): String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(this)

fun Date.toStartOfDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.clear(Calendar.MINUTE)
    calendar.clear(Calendar.SECOND)
    calendar.clear(Calendar.MILLISECOND)
    return calendar.time
}

fun Date.addDays(days: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_MONTH, days)
    return calendar.time
}
fun Date.subtractDays(days: Int) = this.addDays(-days)
fun Date.nextDay() = this.addDays(1)

class DateRange(override val start: Date, override val endInclusive: Date) : ClosedRange<Date>, Iterable<Date> {
    override fun iterator(): Iterator<Date> {
        return DateIterator(this)
    }
}

class DateIterator(val dateRange: DateRange) : Iterator<Date> {
    var current: Date = dateRange.start
    override fun next(): Date {
        current = current.nextDay()
        return current
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}
