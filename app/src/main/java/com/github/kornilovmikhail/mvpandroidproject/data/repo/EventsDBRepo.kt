package com.github.kornilovmikhail.mvpandroidproject.data.repo

import com.github.kornilovmikhail.mvpandroidproject.MyApplication
import com.github.kornilovmikhail.mvpandroidproject.data.db.EventDatabase
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object EventsDBRepo {
    private val eventDatabase: EventDatabase = EventDatabase.getInstance(MyApplication.appContext)

    fun getEvents(): Single<List<Event>> =
        eventDatabase.eventDao().getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun saveEvents(events: List<Event>): Completable =
        Completable.fromAction {
            eventDatabase.eventDao().insertEvents(events)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
