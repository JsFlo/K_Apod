package com.example.jsflo.kapod.data

import com.example.jsflo.kapod.entity.Apod
import io.reactivex.Single
import java.util.*

interface ApodRepo {
    fun getApod(date: Date): Single<Apod>
}