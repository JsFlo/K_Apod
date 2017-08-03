package com.example.jsflo.kapod.data.network.adapters

import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.utils.toJsonRequestFormat
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.joda.time.LocalDate

class ApodJsonAdapter {

    @FromJson
    internal fun apodFromJson(apodJson: ApodJson): Apod {
        // Convert YYYY-MM-DD to Date object
        val date: LocalDate = LocalDate.parse(apodJson.date)
        val apod = Apod(date, apodJson.title,
                apodJson.url, apodJson.explanation,
                apodJson.hdurl, apodJson.media_type,
                apodJson.copyright)
        return apod
    }

    @ToJson
    internal fun apodToJson(apod: Apod): ApodJson {
        return ApodJson(apod.date.toJsonRequestFormat(),
                apod.title, apod.url, apod.explanation,
                apod.hdurl!!, apod.media_type, apod.copyright)
    }
}
