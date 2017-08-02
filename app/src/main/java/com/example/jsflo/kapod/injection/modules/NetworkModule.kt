package com.example.jsflo.kapod.injection.modules

import com.example.jsflo.kapod.data.network.ApodService
import com.example.jsflo.kapod.data.network.adapters.ApodJsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private val BASE_URL = "https://api.nasa.gov/"


    @Provides
    @Singleton
    fun provideApiKey(): String {
        //TODO("API KEY!")
    }

    @Provides
    @Singleton
    fun profvideHeaderAuthorizationInterceptor(apiKey: String): Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newUrl = originalRequest.url().newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

        val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

        chain.proceed(newRequest)
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(ApodJsonAdapter()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(headerAuthorizationInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(headerAuthorizationInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(moshiConverterFactory: MoshiConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideApodApiRestService(retrofit: Retrofit): ApodService = retrofit.create(ApodService::class.java)

}