package com.github.kornilovmikhail.mvpandroidproject.ui.detail

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DetailView : MvpView {
    fun displayEvent(event: Event)
    fun displayError()
    fun showProgressBar()
    fun hideProgressBar()
}
