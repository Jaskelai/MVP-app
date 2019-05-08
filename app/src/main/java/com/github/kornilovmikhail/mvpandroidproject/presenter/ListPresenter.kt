package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.Screens
import com.github.kornilovmikhail.mvpandroidproject.ui.list.ListView
import kotlinx.coroutines.*
import ru.terrakok.cicerone.Router
import kotlin.coroutines.CoroutineContext

@InjectViewState
class ListPresenter(private val eventsRepo: EventsRepo, private val router: Router) :
    MvpPresenter<ListView>() {

    private val job = SupervisorJob()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun getEvents(offset: Int) {
        val handler = CoroutineExceptionHandler { _, _ ->
            viewState.displayError()
        }
        viewState.showProgressBar()
        CoroutineScope(coroutineContext).launch(handler) {
            val events = withContext(Dispatchers.IO) {
                val eventsLocal = eventsRepo.getEvents(offset)
                eventsRepo.cacheEvents(eventsLocal)
                eventsLocal
            }
            withContext(Dispatchers.Main) {
                if (events.isEmpty()) {
                    if (offset != offsetDefault) {
                        viewState.detachOnScrollListeners()
                    }
                } else {
                    viewState.displayEvents(events)
                    viewState.displaySuccess()
                }
            }
        }.invokeOnCompletion {
            viewState.hideProgressBar()
        }
    }

    fun initSharedPreferences(sharedPreferences: SharedPreferences) = eventsRepo.setSharedPreferences(sharedPreferences)

    fun setSharedPrefs(value: Int) = eventsRepo.setCurrentPagination(value)

    fun eventClick(position: Int) = router.navigateTo(Screens.DetailScreen(position))

    fun onCleared() {
        coroutineContext.cancelChildren()
    }

    companion object {
        private const val offsetDefault = 0
    }
}
