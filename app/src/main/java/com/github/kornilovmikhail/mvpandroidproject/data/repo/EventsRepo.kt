package com.github.kornilovmikhail.mvpandroidproject.data.repo

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import io.reactivex.Single

class EventsRepo(
    private val eventsDBRepo: EventsDBRepo,
    private val eventsNetworkRepo: EventsNetworkRepo,
    private val tempEvents: TempEvents
) {
    private var isFirst = true

    fun getEvents(offset: Int): Single<List<Event>> {
        if (isFirst) {
            isFirst = false
            return getEventsFromNetwork(offset)
        }
        if (offset > 0) {
            return getEventsFromNetwork(offset)
        }
        return getEventsFromDB()
    }

    private fun getEventsFromNetwork(offset: Int): Single<List<Event>> = eventsNetworkRepo.getEvents(offset)

    private fun getEventsFromDB(): Single<List<Event>> = eventsDBRepo.getEvents()

    fun cacheEvents(events: List<Event>) {
        if (!tempEvents.events.containsAll(events)) {
            eventsDBRepo.saveEvents(events)
            tempEvents.events.addAll(events)
        }
    }

    fun getCachedEvents(): List<Event> = tempEvents.events
}
