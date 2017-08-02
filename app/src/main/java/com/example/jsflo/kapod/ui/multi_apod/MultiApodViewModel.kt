package com.example.jsflo.kapod.ui.multi_apod

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.jsflo.kapod.data.ApodRepo
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.utils.DateRange
import javax.inject.Inject

class MultiApodViewModel : ViewModel() {

    @Inject
    lateinit var mRepo: ApodRepo

    val mApods: LiveData<List<Apod>> by lazy {
        mRepo.getApods()
    }

    fun getApods(): LiveData<List<Apod>> = mApods

    fun getApods(dateRange: DateRange): LiveData<List<Apod>> {
        return mRepo.getApods(dateRange)
    }
}