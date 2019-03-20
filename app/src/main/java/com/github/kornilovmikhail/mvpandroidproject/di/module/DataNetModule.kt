package com.github.kornilovmikhail.mvpandroidproject.di.module

import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXAPI
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsNetworkRepo
import com.github.kornilovmikhail.mvpandroidproject.di.scope.EventScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataNetModule {
    companion object {
        private const val BASE_URL = "https://api.spacexdata.com/v3/"
    }

    @Provides
    @EventScope
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .build()

    @Provides
    @EventScope
    fun provideSpaceXAPI(retrofit: Retrofit): SpaceXAPI = retrofit.create(SpaceXAPI::class.java)

    @Provides
    @EventScope
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @EventScope
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @EventScope
    fun provideEventsNetworkRepo(spaceXAPI: SpaceXAPI, pagination: Pagination): EventsNetworkRepo =
        EventsNetworkRepo(spaceXAPI, pagination)
}
