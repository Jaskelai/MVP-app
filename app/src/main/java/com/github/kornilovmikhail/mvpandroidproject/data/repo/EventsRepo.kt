package com.github.kornilovmikhail.mvpandroidproject.data.repo

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import io.reactivex.Single

object EventsRepo {
    private var isFirst = true

    fun getEvents(offset: Int): Single<List<Event>> {
        if (isFirst) {
            isFirst = false
            return getEventsFromDB()
        }
        return getEventsFromNetwork(offset)
    }

    private fun getEventsFromNetwork(offset: Int): Single<List<Event>> = EventsNetworkRepo.getEvents(offset)

    private fun getEventsFromDB(): Single<List<Event>> = EventsDBRepo.getEvents()


    fun cacheEvents(events: List<Event>) {
        if (!TempEvents.events.containsAll(events)) {
            EventsDBRepo.saveEvents(events)
            TempEvents.events.addAll(events)
        }
    }
}