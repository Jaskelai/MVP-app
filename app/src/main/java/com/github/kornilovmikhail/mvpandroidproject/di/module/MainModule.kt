package com.github.kornilovmikhail.mvpandroidproject.di.module

import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.scope.EventScope
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    @EventScope
    fun providePresenter(eventsRepo: EventsRepo, pagination: Pagination): MainPresenter =
        MainPresenter(eventsRepo, pagination)
}
