package com.example.jsflo.kapod.data

import android.arch.lifecycle.LiveData
import com.example.jsflo.kapod.data.database.ApodDatabase
import com.example.jsflo.kapod.data.network.ApodService
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.utils.DateRange
import com.example.jsflo.kapod.utils.toJsonRequestFormat
import com.example.jsflo.kapod.utils.toStartOfDay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class ApodRepoImplementation(val apodDatabase: ApodDatabase, val apodApiService: ApodService) : ApodRepo {

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

    override fun getApods(): LiveData<List<Apod>> {
        return apodDatabase.apodDao().getApods()
    }

    override fun getApods(dateRange: DateRange): LiveData<List<Apod>> {
        Observable.just(dateRange)
                .subscribeOn(Schedulers.io())
                .flatMap { Observable.fromIterable(it) }
                .map {
                    // todo: fix this
                    getApod(it)
                            .subscribe()
                }
                .subscribe()

        return getApods()
    }
}