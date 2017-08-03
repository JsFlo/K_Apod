package com.example.jsflo.kapod.data

import android.arch.lifecycle.LiveData
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.utils.DateRange
import io.reactivex.Single
import org.joda.time.LocalDate

interface ApodRepo {
    fun getApod(date: LocalDate): Single<Apod>
    fun getApods(dateRange: DateRange): LiveData<List<Apod>>
    fun getApods(): LiveData<List<Apod>>

    // static sentinel because Single doesn't allow nulls
    companion object {
        val APOD_NOT_FOUND: Apod = Apod()
    }
}

// I may bring in Guava to use Optional because this feels wrong
fun Apod.isValid(): Boolean = this != ApodRepo.APOD_NOT_FOUND
