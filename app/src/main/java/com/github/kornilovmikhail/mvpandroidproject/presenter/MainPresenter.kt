package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.annotation.SuppressLint
import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXService
import com.github.kornilovmikhail.mvpandroidproject.data.network.model.Event
import com.github.kornilovmikhail.mvpandroidproject.ui.main.MainViewInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val mvi: MainViewInterface) : MainPresenterInterface {

    @SuppressLint("CheckResult")
    override fun getEvents() {
        getEventsSingle()
            .doOnSubscribe { mvi.showProgressBar() }
            .doAfterTerminate { mvi.hideProgressBar() }
            .subscribeBy(
                onSuccess = {
                    mvi.displayEvents(it)
                },
                onError = {
                    mvi.displayError()
                }
            )
    }

    fun eventClick(event: Event) = mvi.navigateToMain(event.title,event.details,event.eventDate.toString())

    private fun getEventsSingle(): Single<List<Event>> {
        return SpaceXService.service()
            .loadEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}