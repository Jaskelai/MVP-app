package com.github.kornilovmikhail.mvpandroidproject.ui.links

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface LinksView: MvpView {
    fun setText(article: String?, reddit: String?, wikipedia: String?)
}
