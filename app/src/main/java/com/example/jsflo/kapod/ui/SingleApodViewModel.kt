package com.example.jsflo.kapod.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.jsflo.kapod.data.ApodRepo
import com.example.jsflo.kapod.entity.Apod
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class SingleApodViewModel : ViewModel() {

    @Inject
    lateinit var mRepo: ApodRepo
    val mToday = Date()
    val mApod: MutableLiveData<Apod?> = MutableLiveData()

    fun getApod(date: Date = mToday): LiveData<Apod?> {
        if (mApod.value == null) {
            getApodFromRepo(date)
        }

        return mApod
    }

    private fun getApodFromRepo(date: Date) {
        mRepo.getApod(date)
                .subscribeOn(Schedulers.io())
                .doOnSuccess { mApod.postValue(it) }
                .doOnError { mApod.postValue(null) }
                .subscribe()
    }
}