package com.github.kornilovmikhail.mvpandroidproject.data.repo

import android.content.SharedPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.PaginationPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import io.reactivex.Single

class EventsRepo(
    private val eventsDBRepo: EventsDBRepo,
    private val eventsNetworkRepo: EventsNetworkRepo,
    private val paginationPreferences: PaginationPreferences
) {
    companion object {
        private var isFirst = true
    }

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

    private fun getCurrentPagination(): Int? {
        return paginationPreferences.getCurrentPagination()
    }

    fun setCurrentPagination(pagination: Int) {
        paginationPreferences.setCurrentPagination(pagination)
    }

    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        paginationPreferences.setSharedPrefs(sharedPreferences)
    }

    private fun getEventsFromNetwork(offset: Int): Single<List<Event>> =
        eventsNetworkRepo.getEvents(offset, getCurrentPagination())

    private fun getEventsFromDB(): Single<List<Event>> = eventsDBRepo.getEvents()

    fun cacheEvents(events: List<Event>) = eventsDBRepo.saveEvents(events)
}
