package com.github.kornilovmikhail.mvpandroidproject.data.repository

import android.content.SharedPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event

class EventsRepo(
    private val eventsDBRepo: EventsDBRepo,
    private val eventsNetworkRepo: EventsNetworkRepo,
    private val paginationPreferencesRepo: PaginationPreferencesRepo
) {

    suspend fun getEvents(offset: Int): List<Event> {
        if (isFirst) {
            isFirst = false
            return getEventsFromNetwork(offset)
        }
        if (offset > 0) {
            return getEventsFromNetwork(offset)
        }
        return getEventsFromDB()
    }

    private fun getCurrentPagination(): Int? = paginationPreferencesRepo.getCurrentPagination()

    fun setCurrentPagination(pagination: Int) {
        paginationPreferencesRepo.setCurrentPagination(pagination)
    }

    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        paginationPreferencesRepo.setSharedPrefs(sharedPreferences)
    }

    private suspend fun getEventsFromNetwork(offset: Int): List<Event> =
        eventsNetworkRepo.getEventsAsync(offset, getCurrentPagination()).await()

    private suspend fun getEventsFromDB(): List<Event> = eventsDBRepo.getEventsAsync()

    suspend fun cacheEvents(events: List<Event>) {
        eventsDBRepo.saveEvents(events)
    }

    companion object {
        private var isFirst = true
    }
}
