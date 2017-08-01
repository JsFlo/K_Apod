package com.example.jsflo.kapod.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.jsflo.kapod.entity.Apod

@Database(entities = arrayOf(Apod::class), version = 1, exportSchema = false)
@TypeConverters(ApodDbConverter::class)
abstract class ApodDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao
}