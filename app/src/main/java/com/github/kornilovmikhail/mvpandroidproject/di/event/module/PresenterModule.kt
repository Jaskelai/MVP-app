package com.github.kornilovmikhail.mvpandroidproject.di.event.module

import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.EventScope
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import com.github.kornilovmikhail.mvpandroidproject.presenter.LinksPresenter
import com.github.kornilovmikhail.mvpandroidproject.presenter.ListPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class PresenterModule {
    @Provides
    @EventScope
    fun provideDetailPresenter(eventsRepo: EventsRepo, router: Router): DetailPresenter =
        DetailPresenter(eventsRepo, router)

    @Provides
    @EventScope
    fun provideLinksPresenter(eventsRepo: EventsRepo): LinksPresenter =
        LinksPresenter(eventsRepo)

    @Provides
    @EventScope
    fun provideMainPresenter(eventsRepo: EventsRepo, router: Router): ListPresenter =
        ListPresenter(eventsRepo, router)
}
