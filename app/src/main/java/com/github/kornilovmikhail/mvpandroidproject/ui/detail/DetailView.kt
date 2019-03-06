package com.github.kornilovmikhail.mvpandroidproject.ui.detail

import com.arellomobile.mvp.MvpView

interface DetailView : MvpView {
    fun setText(title: String, details: String, date: String)
}
