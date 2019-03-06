package com.github.kornilovmikhail.mvpandroidproject.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object SpaceXService {
    private const val BASE_URL = "https://api.spacexdata.com/v3/"

    fun service(): SpaceXAPI {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(SpaceXAPI::class.java)
        }
        return retrofit
    }
}
