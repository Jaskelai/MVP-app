package com.github.kornilovmikhail.mvpandroidproject.di.module

import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.scope.EventScope
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailModule {
    @Provides
    @EventScope
    fun providePresenter(eventsRepo: EventsRepo): DetailPresenter =
        DetailPresenter(eventsRepo)
}
