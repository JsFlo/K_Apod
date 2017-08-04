package com.example.jsflo.kapod.data

import android.arch.lifecycle.LiveData
import com.example.jsflo.kapod.data.database.ApodDatabase
import com.example.jsflo.kapod.data.network.ApodService
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.utils.DateRange
import com.example.jsflo.kapod.utils.toJsonRequestFormat
import com.google.common.base.Optional
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.joda.time.LocalDate

class ApodRepoImplementation(val apodDatabase: ApodDatabase, val apodApiService: ApodService) : ApodRepo {

    private fun addApod(apod: Apod) {
        apodDatabase.apodDao().addApod(apod)
    }

    override fun getApod(date: LocalDate): Single<Optional<Apod>> {
        return getApodFromDb(date)
                .flatMap {
                    if (it.isPresent) {
                        Single.just(it)
                    } else {
                        getApodFromNetwork(date)
                    }
                }
    }


    override fun getApods(): LiveData<List<Apod>> {
        return apodDatabase.apodDao().getApods()
    }

    override fun getApods(dateRange: DateRange): LiveData<List<Apod>> {
        Observable.just(dateRange)
                .subscribeOn(Schedulers.io())
                .flatMap { Observable.fromIterable(it) }
                .map { getApod(it).subscribe() }
                .subscribe()

        return getApods()
    }

    private fun getApodFromDb(date: LocalDate): Single<Optional<Apod>> {
        return Observable.fromCallable { Optional.fromNullable(apodDatabase.apodDao().getApod(date)) }
                .subscribeOn(Schedulers.io())
                .single(Optional.absent())
    }

    private fun getApodFromNetwork(date: LocalDate): Single<Optional<Apod>> {
        return apodApiService.getApod(date = date.toJsonRequestFormat())
                .map { Optional.fromNullable(it) }
                .onErrorResumeNext { Single.just(Optional.absent()) }
                .doOnSuccess { if (it.isPresent) addApod(it.get()) }
    }
}