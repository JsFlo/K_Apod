package com.example.jsflo.kapod.utils

import java.text.SimpleDateFormat
import java.util.*

private val APOD_PRETTY_FORMATTER = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
private val APOD_JSON_REQUEST_FORMATTER = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

fun Date.toPrettyFormat(): String = APOD_PRETTY_FORMATTER.format(this)
fun Date.toJsonRequestFormat(): String = APOD_JSON_REQUEST_FORMATTER.format(this)

fun Date.toStartOfDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.clear(Calendar.MINUTE)
    calendar.clear(Calendar.SECOND)
    calendar.clear(Calendar.MILLISECOND)
    return calendar.time
}

fun Date.addTimeIntervals(timeInterval: TimeInterval, number: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    when (timeInterval) {
        TimeInterval.DAY -> calendar.add(Calendar.DAY_OF_MONTH, number)
        TimeInterval.WEEK -> calendar.add(Calendar.WEEK_OF_MONTH, number)
        TimeInterval.YEAR -> calendar.add(Calendar.YEAR, number)
    }
    return calendar.time
}

fun Date.nextDay() = addTimeIntervals(TimeInterval.DAY, 1)
operator fun Date.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, 1)
operator fun Date.plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)
operator fun Date.minus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, -1)
operator fun Date.minus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.timeInterval, -timeIntervals.number)

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)
class DateRange(override val start: Date, override val endInclusive: Date) : ClosedRange<Date>, Iterable<Date> {
    override fun iterator(): Iterator<Date> {
        return DateIterator(this)
    }
}

class DateIterator(val dateRange: DateRange) : Iterator<Date> {
    var current: Date = dateRange.start
    override fun next(): Date {
        val result = current
        current = current.nextDay()
        return result
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}
