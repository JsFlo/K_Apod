package com.example.jsflo.kapod.data

import com.example.jsflo.kapod.utils.toJsonRequestFormat
import com.example.jsflo.kapod.utils.toStartOfDay
import com.example.jsflo.kapod.data.database.ApodDatabase
import com.example.jsflo.kapod.data.network.ApodService
import com.example.jsflo.kapod.entity.Apod
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class MyApodRepo(val apodDatabase: ApodDatabase, val apodApiService: ApodService) : ApodRepo {

    private fun addApod(apod: Apod) {
        apodDatabase.apodDao().addApod(apod)
    }

    // check if we have the apod in the database otherwise (onErrorResumeNext) make an api call
    override fun getApod(date: Date): Single<Apod> {
        return Observable.fromCallable { apodDatabase.apodDao().getApod(date.toStartOfDay()) }
                .subscribeOn(Schedulers.io())
                .singleOrError()
                .onErrorResumeNext {
                    apodApiService.getApod(date = date.toJsonRequestFormat())
                            .doOnSuccess { addApod(it) }
                }
    }
}