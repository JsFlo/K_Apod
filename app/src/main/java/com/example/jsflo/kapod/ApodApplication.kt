package com.example.jsflo.kapod

import android.app.Application
import com.example.jsflo.kapod.injection.components.ApodComponent
import com.example.jsflo.kapod.injection.components.DaggerApodComponent
import com.example.jsflo.kapod.injection.modules.AppModule
import net.danlew.android.joda.JodaTimeAndroid

class ApodApplication : Application() {
    val apodComponent: ApodComponent by lazy {
        DaggerApodComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}