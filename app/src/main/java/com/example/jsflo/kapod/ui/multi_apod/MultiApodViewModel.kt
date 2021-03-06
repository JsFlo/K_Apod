package com.example.jsflo.kapod.ui.multi_apod

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.jsflo.kapod.data.ApodRepo
import com.example.jsflo.kapod.entity.Apod
import com.example.jsflo.kapod.injection.components.ApodComponent
import com.example.jsflo.kapod.utils.DateRange
import javax.inject.Inject

class MultiApodViewModel : ViewModel(), ApodComponent.Injectable {

    @Inject
    lateinit var mRepo: ApodRepo

    fun getApods(dateRange: DateRange): LiveData<List<Apod>> {
        return mRepo.getApods(dateRange)
    }

    override fun inject(apodComponent: ApodComponent) {
        apodComponent.inject(this)
    }
}