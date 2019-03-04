package com.github.kornilovmikhail.mvpandroidproject.ui.main

import com.arellomobile.mvp.MvpView
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event

interface MainView : MvpView {
    fun displayEvents(listEvents: List<Event>)
    fun displaySuccess()
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
    fun navigateToMain(position: Int)
    fun detachOnScrollListeners()
}
