package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.TempEvents
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksView

@InjectViewState
class LinksPresenter : MvpPresenter<LinksView>() {
    fun getLinks(position: Int) {
        val links = TempEvents.events[position].links
        viewState.setText(links.linkReddit, links.linkArticle, links.linkWikipedia)
    }
}
