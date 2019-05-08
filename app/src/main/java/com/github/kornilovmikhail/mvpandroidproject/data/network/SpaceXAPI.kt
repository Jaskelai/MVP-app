package com.github.kornilovmikhail.mvpandroidproject.data.network

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXAPI {
    @GET("history")
    fun loadEventsAsync(@Query("offset") offset: Int, @Query("limit") limit: Int?): Deferred<List<Event>>
}
