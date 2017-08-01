package com.example.jsflo.kapod.utils

import java.text.SimpleDateFormat
import java.util.*

private val APOD_PRETTY_FORMATTER = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
private val APOD_JSON_REQUEST_FORMATTER = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun Date.toPrettyFormat(): String = APOD_PRETTY_FORMATTER.format(this)
fun Date.toJsonRequestFormat(): String = APOD_JSON_REQUEST_FORMATTER.format(this)

// Probably shouldn't use Date if I'm not going to use minute/second/milli but Extension functions
fun Date.toStartOfDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.clear(Calendar.MINUTE)
    calendar.clear(Calendar.SECOND)
    calendar.clear(Calendar.MILLISECOND)
    return calendar.time
}