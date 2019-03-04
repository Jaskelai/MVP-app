package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.TempEvents
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailView

class DetailPresenter : MvpPresenter<DetailView>() {
    fun getEvent(position: Int) {
        val event = TempEvents.events[position]
        viewState.setText(event.title, event.details, event.eventDate.toString())
    }
}