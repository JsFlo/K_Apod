package com.example.jsflo.kapod.injection.modules

import com.example.jsflo.kapod.data.ApodRepo
import com.example.jsflo.kapod.data.MyApodRepo
import com.example.jsflo.kapod.data.database.ApodDatabase
import com.example.jsflo.kapod.data.network.ApodService
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun provideBasicApodRepository(apodDatabase: ApodDatabase, apodApiService: ApodService): ApodRepo {
        return MyApodRepo(apodDatabase, apodApiService)
    }
}