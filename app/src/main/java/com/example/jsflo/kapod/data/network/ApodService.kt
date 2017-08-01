package com.example.jsflo.kapod.data.network

import com.example.jsflo.kapod.entity.Apod
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {

    @GET("/planetary/apod")
    fun getApod(@Query("date") date: String = "",
                @Query("hd") highResolutionImage: Boolean = false): Single<Apod>

}