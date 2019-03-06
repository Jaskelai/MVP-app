package com.github.kornilovmikhail.mvpandroidproject.ui.links

import com.arellomobile.mvp.MvpView

interface LinksView: MvpView {
    fun setText(article: String?, reddit: String?, wikipedia: String?)
}
