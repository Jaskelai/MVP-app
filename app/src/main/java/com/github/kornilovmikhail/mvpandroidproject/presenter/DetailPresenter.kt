package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.Screens
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class DetailPresenter(private val eventsRepo: EventsRepo, private val router: Router) : MvpPresenter<DetailView>() {
    fun getEvent(position: Int) {
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

    fun onIconClicked(position: Int) {
        router.navigateTo(Screens.LinksScreen(position))
    }
}
