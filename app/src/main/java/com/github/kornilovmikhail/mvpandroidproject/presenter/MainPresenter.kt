package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
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
            EventsDBRepo.getEvents()
                .subscribeBy(
                    onSuccess = {
                        println(it)
                        if (it.isEmpty()) {
                            getEventsFromNetwork(offset)
                        } else {
                            if (!TempEvents.events.containsAll(it)) {
                                TempEvents.events.addAll(it)
                            }
                            viewState.displayEvents(TempEvents.events)
                            viewState.hideProgressBar()
                        }
                    }
                )
        }
    }

    @SuppressLint("CheckResult")
    private fun getEventsFromNetwork(offset: Int) {
        EventsNetworkRepo.getEvents(offset)
            .doOnSubscribe {
                viewState.showProgressBar() }
            .doAfterTerminate {
                viewState.hideProgressBar() }
            .subscribeBy(
                onSuccess = {
                    if (it.isEmpty()) {
                        viewState.detachOnScrollListeners()
                    }
                    if (!TempEvents.events.containsAll(it)) {
                        EventsDBRepo.saveEvents(it)
                        TempEvents.events.addAll(it)
                        viewState.displayEvents(TempEvents.events)
                        viewState.displaySuccess()
                    }
                },
                onError = {
                    viewState.displayError()
                }
            )
    }

    fun initSharedPrefs(sharedPreferences: SharedPreferences) {
        Pagination.setSharedPrefs(sharedPreferences)
    }

    fun setSharedPrefs(value: Int) {
        Pagination.setCurrentPagination(value)
    }

    fun eventClick(position: Int) = viewState.navigateToMain(position)

}