package com.example.jsflo.kapod.injection.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(application: Application) {

    val mApplication = application
    val notUsed = 3

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return mApplication
    }
}