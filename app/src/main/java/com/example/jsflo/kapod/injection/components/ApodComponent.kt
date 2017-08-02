package com.example.jsflo.kapod.injection.components

import com.example.jsflo.kapod.injection.modules.AppModule
import com.example.jsflo.kapod.injection.modules.DatabaseModule
import com.example.jsflo.kapod.injection.modules.NetworkModule
import com.example.jsflo.kapod.injection.modules.RepoModule
import com.example.jsflo.kapod.ui.SingleApodViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class,
        DatabaseModule::class, RepoModule::class))
interface ApodComponent {

    fun inject(singleApodViewModel: SingleApodViewModel)
}