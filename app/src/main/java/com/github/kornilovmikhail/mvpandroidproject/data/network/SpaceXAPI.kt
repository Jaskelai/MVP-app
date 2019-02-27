package com.github.kornilovmikhail.mvpandroidproject.data.network

import com.github.kornilovmikhail.mvpandroidproject.data.network.response.Event
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXAPI {
    @GET("history?limit=12")
    fun loadEvents(@Query("offset") offset: Int): Single<List<Event>>
}