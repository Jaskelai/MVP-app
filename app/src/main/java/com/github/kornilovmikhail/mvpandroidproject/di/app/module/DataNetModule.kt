package com.github.kornilovmikhail.mvpandroidproject.di.app.module

import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXAPI
import com.github.kornilovmikhail.mvpandroidproject.di.app.scope.ApplicationScope
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataNetModule {
    companion object {
        private const val BASE_URL = "https://api.spacexdata.com/v3/"
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(coroutineCallAdapterFactory)
        .build()

    @Provides
    @ApplicationScope
    fun provideSpaceXAPI(retrofit: Retrofit): SpaceXAPI = retrofit.create(SpaceXAPI::class.java)

    @Provides
    @ApplicationScope
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @ApplicationScope
    fun provideRxJava2CallAdapterFactory(): CoroutineCallAdapterFactory = CoroutineCallAdapterFactory()
}
