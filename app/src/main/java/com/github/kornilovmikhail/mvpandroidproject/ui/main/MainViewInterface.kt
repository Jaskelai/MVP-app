package com.github.kornilovmikhail.mvpandroidproject.ui.main

import com.github.kornilovmikhail.mvpandroidproject.data.network.model.Event

interface MainViewInterface {
    fun displayEvents(listEvents: List<Event>)
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
}
