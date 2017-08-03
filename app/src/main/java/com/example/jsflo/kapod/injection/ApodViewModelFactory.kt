package com.example.jsflo.kapod.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.jsflo.kapod.ApodApplication
import com.example.jsflo.kapod.injection.components.ApodComponent

class ApodViewModelFactory(val mApplication: ApodApplication) : ViewModelProvider.NewInstanceFactory() {

    // Creates the view models and calls inject with the apod component
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        val t: T = super.create(modelClass)
        if (t is ApodComponent.Injectable) {
            t.inject(mApplication.apodComponent)
        }
        return t
    }
}