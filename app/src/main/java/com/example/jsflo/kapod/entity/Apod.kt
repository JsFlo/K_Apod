package com.example.jsflo.kapod.entity

import java.util.Date

data class Apod(var date: Date, var title: String,
                var url: String, var explanation: String,
                var hdurl: String?,
                var media_type: String,
                var copyright: String?)