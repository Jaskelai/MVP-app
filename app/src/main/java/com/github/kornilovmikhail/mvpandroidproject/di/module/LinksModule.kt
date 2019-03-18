package com.github.kornilovmikhail.mvpandroidproject.di.module

import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.scope.EventScope
import com.github.kornilovmikhail.mvpandroidproject.presenter.LinksPresenter
import dagger.Module
import dagger.Provides

@Module
class LinksModule {
    @Provides
    @EventScope
    fun providePresenter(eventsRepo: EventsRepo): LinksPresenter =
        LinksPresenter(eventsRepo)
}
