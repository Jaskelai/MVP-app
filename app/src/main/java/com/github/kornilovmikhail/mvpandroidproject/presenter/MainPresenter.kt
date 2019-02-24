package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.annotation.SuppressLint
import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXService
import com.github.kornilovmikhail.mvpandroidproject.data.network.model.Event
import com.github.kornilovmikhail.mvpandroidproject.ui.main.MainViewInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


class MainPresenter(private val mvi: MainViewInterface) : MainPresenterInterface {

    private var listEvents: List<Event>

    init {
        listEvents = ArrayList()
    }

    @SuppressLint("CheckResult")
    override fun getEvents() {
        getEventsSingle().subscribeBy(
            onSuccess = {
                listEvents = it
                mvi.displayEvents(listEvents)
            },
            onError = {
                mvi.displayError()
            }
        )
    }

    private fun getEventsSingle(): Single<List<Event>> {
        return SpaceXService.service()
            .loadEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
