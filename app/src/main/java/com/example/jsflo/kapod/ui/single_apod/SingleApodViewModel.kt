package com.example.jsflo.kapod.ui.single_apod

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.jsflo.kapod.data.ApodRepo
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.injection.components.ApodComponent
import io.reactivex.schedulers.Schedulers
import org.joda.time.LocalDate
import javax.inject.Inject

class SingleApodViewModel : ViewModel(), ApodComponent.Injectable {

    @Inject
    lateinit var mRepo: ApodRepo
    val mToday = LocalDate()
    val mApod: MutableLiveData<Apod?> = MutableLiveData()

    fun getApod(date: LocalDate = mToday): LiveData<Apod?> {
        if (mApod.value == null) {
            getApodFromRepo(date)
        }

        return mApod
    }

    private fun getApodFromRepo(date: LocalDate) {
        mRepo.getApod(date)
                .subscribeOn(Schedulers.io())
                .doOnSuccess { mApod.postValue(it.get()) }
                .doOnError { mApod.postValue(null) }
                .subscribe()
    }

    override fun inject(apodComponent: ApodComponent) {
        apodComponent.inject(this)
    }
}