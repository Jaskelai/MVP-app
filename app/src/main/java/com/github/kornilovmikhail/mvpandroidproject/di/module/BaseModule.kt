package com.github.kornilovmikhail.mvpandroidproject.di.module

import com.github.kornilovmikhail.mvpandroidproject.data.PaginationPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsDBRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsNetworkRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.scope.EventScope
import dagger.Module
import dagger.Provides

@Module
class BaseModule {
    @Provides
    @EventScope
    fun provideEventsRepo(
        eventsDBRepo: EventsDBRepo,
        eventsNetworkRepo: EventsNetworkRepo
    ): EventsRepo = EventsRepo(eventsDBRepo, eventsNetworkRepo, providePagination())

    @Provides
    @EventScope
    fun providePagination(): PaginationPreferences = PaginationPreferences()
}
