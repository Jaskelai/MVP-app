package com.github.kornilovmikhail.mvpandroidproject.di.module

import android.support.v7.widget.RecyclerView
import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.di.scope.EventScope
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter.EventAdapter
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    @EventScope
    fun provideAdapter(mainPresenter: MainPresenter): RecyclerView.Adapter<EventAdapter.EventHolder> =
        EventAdapter(mainPresenter)

    @Provides
    @EventScope
    fun providePresenter(eventsRepo: EventsRepo, pagination: Pagination): MainPresenter =
        MainPresenter(eventsRepo, pagination)
}
