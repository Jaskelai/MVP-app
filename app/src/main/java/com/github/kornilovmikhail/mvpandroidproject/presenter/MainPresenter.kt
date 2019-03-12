package com.github.kornilovmikhail.mvpandroidproject.presenter

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.main.MainView
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class MainPresenter(private val repo: EventsRepo) : MvpPresenter<MainView>() {

    companion object {
        private const val offsetDefault = 0
    }

    override fun onFirstViewAttach() {
        getEvents(offsetDefault)
    }

    fun getEvents(offset: Int) {
        repo.getEvents(offset)
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
                        } else {
                            getEvents(offset)
                        }
                    } else {
                        repo.cacheEvents(it)
                        viewState.displayEvents(it)
                        viewState.displaySuccess()
                    }
                },
                onError = {
                    viewState.displayError()
                }
            )
    }

    fun initSharedPrefs(sharedPreferences: SharedPreferences) = Pagination().setSharedPrefs(sharedPreferences)

    fun setSharedPrefs(value: Int) = Pagination().setCurrentPagination(value)

    fun eventClick(position: Int) = viewState.navigateToMain(position)
}
