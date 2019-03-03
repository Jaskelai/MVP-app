package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.TempEvents
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsDBRepo
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsNetworkRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.main.MainView
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @SuppressLint("CheckResult")
    fun getEvents(offset: Int) {
        if (offset > 0) {
            getEventsFromNetwork(offset)
        } else {
            EventsDBRepo().getEvents()
                .subscribeBy(
                    onSuccess = {
                        if (it.isEmpty()) {
                            getEventsFromNetwork(offset)
                        } else {
                            if (!TempEvents.events.containsAll(it)) {
                                TempEvents.events.addAll(it)
                            }
                            viewState.displayEvents(TempEvents.events)
                        }
                    }
                )
        }
    }

    @SuppressLint("CheckResult")
    private fun getEventsFromNetwork(offset: Int) {
        EventsNetworkRepo.getEvents(offset)
            .doOnSubscribe { viewState.showProgressBar() }
            .doAfterTerminate { viewState.hideProgressBar() }
            .subscribeBy(
                onSuccess = {
                    if (it.isEmpty()) {
                        viewState.detachOnScrollListeners()
                    }
                    if (!TempEvents.events.containsAll(it)) {
                        EventsDBRepo().saveEvents(it)
                        TempEvents.events.addAll(it)
                        viewState.displayEvents(TempEvents.events)
                    }
                },
                onError = {
                    viewState.displayError()
                }
            )
    }

    fun eventClick(event: Event) = viewState.navigateToMain(event.title, event.details, event.eventDate.toString())

}