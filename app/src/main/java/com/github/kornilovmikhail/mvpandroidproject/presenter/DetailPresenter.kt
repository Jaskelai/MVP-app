package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.Screens
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailView
import kotlinx.coroutines.*
import ru.terrakok.cicerone.Router
import kotlin.coroutines.CoroutineContext

@InjectViewState
class DetailPresenter(private val eventsRepo: EventsRepo, private val router: Router) : MvpPresenter<DetailView>() {
    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun getEvent(position: Int) {
        val handler = CoroutineExceptionHandler { _, _ ->
            viewState.displayError()
        }
        viewState.showProgressBar()
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val events = withContext(Dispatchers.IO) {
                eventsRepo.getEvents(0)
            }
            withContext(Dispatchers.Main) {
                viewState.displayEvent(events[position])
            }
        }.invokeOnCompletion {
            viewState.hideProgressBar()
        }
    }

    fun onIconClicked(position: Int) {
        router.navigateTo(Screens.LinksScreen(position))
    }

    fun onCleared() {
        coroutineContext.cancelChildren()
    }
}
