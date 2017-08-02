package com.example.jsflo.kapod.data

import android.arch.lifecycle.LiveData
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.utils.DateRange
import io.reactivex.Single
import java.util.*

interface ApodRepo {
    fun getApod(date: Date): Single<Apod>
    fun getApods(dateRange: DateRange): LiveData<List<Apod>>
    fun getApods(): LiveData<List<Apod>>
}