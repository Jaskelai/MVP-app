package com.github.kornilovmikhail.mvpandroidproject.data.repo

import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object EventsNetworkRepo{
     fun getEvents(offset: Int): Single<List<Event>> =
        SpaceXService.service()
            .loadEvents(offset, Pagination.getCurrentPagination())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
