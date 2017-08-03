package com.example.jsflo.kapod.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.jsflo.kapod.entity.Apod
import org.joda.time.LocalDate

@Dao
interface ApodDao {
    companion object {
        const val DATE = "date"
        const val TABLE_APOD = "apod"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addApod(apod: Apod)

    // TODO: arg0 is a hack because room + kotlin aren't getting along
    @Query("SELECT * FROM $TABLE_APOD where $DATE = :arg0")
    fun getApod(date: LocalDate): Apod

    @Query("SELECT * FROM apod")
    fun getApods(): LiveData<List<Apod>>
}