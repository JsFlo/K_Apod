package com.example.jsflo.kapod.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.Date

@Entity
data class Apod(@PrimaryKey var date: Date = Date(), var title: String = "",
                var url: String = "", var explanation: String = "",
                var hdurl: String? = null,
                var media_type: String = "",
                var copyright: String? = null)