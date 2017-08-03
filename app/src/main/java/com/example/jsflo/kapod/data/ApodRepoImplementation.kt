package com.example.jsflo.kapod.data

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.jsflo.kapod.data.database.ApodDatabase
import com.example.jsflo.kapod.data.network.ApodService
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.utils.DateRange
import com.example.jsflo.kapod.utils.toJsonRequestFormat
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.joda.time.LocalDate

class ApodRepoImplementation(val apodDatabase: ApodDatabase, val apodApiService: ApodService) : ApodRepo {

    private fun addApod(apod: Apod) {
        apodDatabase.apodDao().addApod(apod)
    }

    override fun getApod(date: LocalDate): Single<Apod> {
        return getApodFromDb(date)
                .flatMap {
                    if (it.isValid()) {
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

    private fun getApodFromDb(date: LocalDate): Single<Apod> {
        return Observable.fromCallable { apodDatabase.apodDao().getApod(date) ?: ApodRepo.APOD_NOT_FOUND }
                .subscribeOn(Schedulers.io())
                .single(ApodRepo.APOD_NOT_FOUND)
    }

    private fun getApodFromNetwork(date: LocalDate): Single<Apod> {
        return apodApiService.getApod(date = date.toJsonRequestFormat())
                .onErrorResumeNext { Single.just(ApodRepo.APOD_NOT_FOUND) }
                .doOnSuccess { if (it.isValid()) addApod(it) }
    }
}