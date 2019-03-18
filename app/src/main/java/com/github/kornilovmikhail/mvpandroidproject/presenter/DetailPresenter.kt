package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailView

@InjectViewState
class DetailPresenter(private val eventsRepo: EventsRepo) : MvpPresenter<DetailView>() {
    fun getEvent(position: Int) {
        val event = eventsRepo.getCachedEvents()[position]
        viewState.setText(event.title, event.details, event.eventDate.toString())
    }
}
