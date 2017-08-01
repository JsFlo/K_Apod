package com.example.jsflo.kapod.data.network.adapters

import com.example.jsflo.kapod.entity.Apod
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ApodJsonAdapter {

    companion object {
        private val APOD_JSON_DATE_FORMAT = "yyyy-MM-dd"
        private val APOD_DATE_FORMATTER = SimpleDateFormat(APOD_JSON_DATE_FORMAT, Locale.getDefault())
    }

    @FromJson
    internal fun apodFromJson(apodJson: ApodJson): Apod {
        // Convert YYYY-MM-DD to Date object
        var date: Date
        try {
            date = APOD_DATE_FORMATTER.parse(apodJson.date)
        } catch (e: ParseException) {
            e.printStackTrace()
            // TODO: Possible bug if parsing fails this will overwrite the apod for "today" (Date())
            date = Date()
        }

        val apod = Apod(date, apodJson.title,
                apodJson.url, apodJson.explanation,
                apodJson.hdurl, apodJson.media_type,
                apodJson.copyright)
        return apod
    }

    @ToJson
    internal fun apodToJson(apod: Apod): ApodJson {
        return ApodJson(APOD_DATE_FORMATTER.format(apod.date),
                apod.title, apod.url, apod.explanation,
                apod.hdurl!!, apod.media_type, apod.copyright)
    }
}
