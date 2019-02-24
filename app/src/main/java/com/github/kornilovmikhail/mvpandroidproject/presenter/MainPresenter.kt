package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.github.kornilovmikhail.mvpandroidproject.data.network.SpaceXService
import com.github.kornilovmikhail.mvpandroidproject.data.network.model.Event
import com.github.kornilovmikhail.mvpandroidproject.ui.main.MainViewInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainPresenter(private val mvi: MainViewInterface) : MainPresenterInterface {

    private var listEvents: List<Event>
    private val compositeDisposable: CompositeDisposable

    init {
        listEvents = ArrayList()
        compositeDisposable = CompositeDisposable()
    }

    override fun getEvents() {
        compositeDisposable.add(getEventsDisposable())
    }

    private fun getEventsDisposable(): Disposable {
        return SpaceXService.service()
            .loadEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                listEvents = response
            }
            .subscribe({ mvi.displayEvents(listEvents) }, { mvi.displayError() })

    }

    fun dispose() {
        compositeDisposable.clear()
    }

}