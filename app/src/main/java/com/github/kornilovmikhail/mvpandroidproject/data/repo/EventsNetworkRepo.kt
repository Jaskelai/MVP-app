package com.github.kornilovmikhail.mvpandroidproject.data.repo

import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventsNetworkRepo(private val spaceXAPI: SpaceXAPI, private val pagination: Pagination) {
    fun getEvents(offset: Int): Single<List<Event>> =
        spaceXAPI
            .loadEvents(offset, pagination.getCurrentPagination())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
