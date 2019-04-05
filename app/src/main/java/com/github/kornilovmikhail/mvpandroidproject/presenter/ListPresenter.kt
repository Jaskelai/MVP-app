package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.Screens
import com.github.kornilovmikhail.mvpandroidproject.ui.list.ListView
import io.reactivex.rxkotlin.subscribeBy
import ru.terrakok.cicerone.Router

@InjectViewState
class ListPresenter(private val eventsRepo: EventsRepo, private val router: Router) :
    MvpPresenter<ListView>() {

    companion object {
        private const val offsetDefault = 0
    }

    fun getEvents(offset: Int) {
        eventsRepo.getEvents(offset)
            .doOnSubscribe {
                viewState.showProgressBar()
            }
            .doAfterTerminate {
                viewState.hideProgressBar()
            }
            .subscribeBy(
                onSuccess = {
                    if (it.isEmpty()) {
                        if (offset != offsetDefault) {
                            viewState.detachOnScrollListeners()
                        }
                    } else {
                        eventsRepo.cacheEvents(it)
                        viewState.displayEvents(it)
                        viewState.displaySuccess()
                    }
                },
                onError = {
                    viewState.displayError()
                }
            )
    }

    fun initSharedPreferences(sharedPreferences: SharedPreferences) = eventsRepo.setSharedPreferences(sharedPreferences)

    fun setSharedPrefs(value: Int) = eventsRepo.setCurrentPagination(value)

    fun eventClick(position: Int) = router.navigateTo(Screens.DetailScreen(position))
}
