package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksView
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class LinksPresenter(private val eventsRepo: EventsRepo) : MvpPresenter<LinksView>() {

    fun getLinks(position: Int) {
        eventsRepo.getEvents(0)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    viewState.displayEvent(it[position])
                },
                onError =
                {
                    viewState.displayError()
                }
            )
    }
}
