package com.github.kornilovmikhail.mvpandroidproject.di.event.module

import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.EventScope
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import com.github.kornilovmikhail.mvpandroidproject.presenter.LinksPresenter
import com.github.kornilovmikhail.mvpandroidproject.presenter.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    @EventScope
    fun provideDetailPresenter(eventsRepo: EventsRepo): DetailPresenter =
        DetailPresenter(eventsRepo)

    @Provides
    @EventScope
    fun provideLinksPresenter(eventsRepo: EventsRepo): LinksPresenter =
        LinksPresenter(eventsRepo)

    @Provides
    @EventScope
    fun provideMainPresenter(eventsRepo: EventsRepo): ListPresenter =
        ListPresenter(eventsRepo)
}
