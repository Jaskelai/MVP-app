package com.github.kornilovmikhail.mvpandroidproject.data.network

import com.github.kornilovmikhail.mvpandroidproject.data.network.model.ListEvents
import io.reactivex.Single
import retrofit2.http.GET

interface SpaceXAPI {
    @GET("history")
    fun loadCities(): Single<ListEvents>
}