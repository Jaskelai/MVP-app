package com.github.kornilovmikhail.mvpandroidproject.ui.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun displayEvents(listEvents: List<Event>)
    fun displaySuccess()
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
    fun navigateToMain(position: Int)
    fun detachOnScrollListeners()
}
