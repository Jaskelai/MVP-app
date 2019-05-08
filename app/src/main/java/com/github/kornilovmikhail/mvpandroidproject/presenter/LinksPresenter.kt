package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@InjectViewState
class LinksPresenter(private val eventsRepo: EventsRepo) : MvpPresenter<LinksView>() {

    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun getLinks(position: Int) {
        val handler = CoroutineExceptionHandler { _, _ ->
            viewState.displayError()
        }
        viewState.showProgressBar()
        CoroutineScope(coroutineContext).launch(handler) {
            val events = withContext(Dispatchers.IO) {
                eventsRepo.getEvents(0)
            }
            withContext(Dispatchers.Main) {
                try {
                    viewState.displayEvent(events[position])
                } catch (e: Throwable) {
                    viewState.displayError()
                }
            }
        }.invokeOnCompletion {
            viewState.hideProgressBar()
        }
    }

    fun onCleared() {
        coroutineContext.cancelChildren()
    }
}
