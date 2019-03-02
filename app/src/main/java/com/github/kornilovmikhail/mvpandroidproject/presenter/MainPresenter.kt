package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXService
import com.github.kornilovmikhail.mvpandroidproject.data.network.response.Event
import com.github.kornilovmikhail.mvpandroidproject.ui.main.MainView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @SuppressLint("CheckResult")
    fun getEvents(offset: Int) {
        getEventsSingle(offset)
            .doOnSubscribe { viewState.showProgressBar() }
            .doAfterTerminate { viewState.hideProgressBar() }
            .subscribeBy(
                onSuccess = {
                    if (it.isEmpty()) {
                        viewState.detachOnScrollListeners()
                    }
                    viewState.displayEvents(it)
                },
                onError = {
                    viewState.displayError()
                }
            )
    }

    fun eventClick(event: Event) = viewState.navigateToMain(event.title, event.details, event.eventDate.toString())

    private fun getEventsSingle(offset: Int): Single<List<Event>> {
        return SpaceXService.service()
            .loadEvents(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}