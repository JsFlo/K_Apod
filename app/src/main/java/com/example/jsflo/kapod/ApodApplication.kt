package com.example.jsflo.kapod

import android.app.Application
import com.example.jsflo.kapod.injection.components.*
import com.example.jsflo.kapod.injection.modules.AppModule

class ApodApplication : Application() {
    val apodComponent: ApodComponent by lazy {
        DaggerApodComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}