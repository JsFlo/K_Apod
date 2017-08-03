package com.example.jsflo.kapod.utils

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

val PRETTY_DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd")
val JSON_DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd")

// Creates an SDF object on every call but it works for small demo
fun LocalDate.toPrettyFormat(): String = toString(PRETTY_DATE_FORMAT)

fun LocalDate.toJsonRequestFormat(): String = toString(JSON_DATE_FORMAT)

class DateRange(override val start: LocalDate, override val endInclusive: LocalDate) : ClosedRange<LocalDate>, Iterable<LocalDate> {
    override fun iterator(): Iterator<LocalDate> {
        return DateIterator(this)
    }
}

class DateIterator(val dateRange: DateRange) : Iterator<LocalDate> {
    var current: LocalDate = dateRange.start
    override fun next(): LocalDate {
        current = current.plusDays(1)
        return current
    }

    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}
