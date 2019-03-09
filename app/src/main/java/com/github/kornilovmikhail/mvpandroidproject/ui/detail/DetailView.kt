package com.github.kornilovmikhail.mvpandroidproject.ui.detail

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DetailView : MvpView {
    fun setText(title: String, details: String, date: String)
}
