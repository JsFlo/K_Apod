package com.example.jsflo.kapod.injection.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.example.jsflo.kapod.data.database.ApodDao
import com.example.jsflo.kapod.data.database.ApodDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application): ApodDatabase {
        return Room.databaseBuilder(context, ApodDatabase::class.java, "apod_db.db").build()
    }

    @Provides
    @Singleton
    fun provideApodDao(apodDatabase: ApodDatabase): ApodDao {
        return apodDatabase.apodDao()
    }
}