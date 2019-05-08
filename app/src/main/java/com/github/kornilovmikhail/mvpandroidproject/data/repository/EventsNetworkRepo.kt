package com.github.kornilovmikhail.mvpandroidproject.data.repository

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXAPI
import kotlinx.coroutines.Deferred

class EventsNetworkRepo(private val spaceXAPI: SpaceXAPI) {
    fun getEventsAsync(offset: Int, pagination: Int?): Deferred<List<Event>> =
        spaceXAPI.loadEventsAsync(offset, pagination)
}
