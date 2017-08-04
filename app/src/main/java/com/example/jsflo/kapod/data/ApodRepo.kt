package com.example.jsflo.kapod.data

import android.arch.lifecycle.LiveData
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.utils.DateRange
import com.google.common.base.Optional
import io.reactivex.Single
import org.joda.time.LocalDate

interface ApodRepo {
    fun getApod(date: LocalDate): Single<Optional<Apod>>
    fun getApods(dateRange: DateRange): LiveData<List<Apod>>
    fun getApods(): LiveData<List<Apod>>
}
