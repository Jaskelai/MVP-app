package com.github.kornilovmikhail.mvpandroidproject.data.repository

import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.EventDao
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event

class EventsDBRepo(private val eventDao: EventDao) {

    suspend fun getEventsAsync(): List<Event> = eventDao.getEvents()

    suspend fun saveEvents(events: List<Event>) = eventDao.insertEvents(events)
}
