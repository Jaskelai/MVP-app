package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksView

@InjectViewState
class LinksPresenter(private val eventsRepo: EventsRepo) : MvpPresenter<LinksView>() {

    fun getLinks(position: Int) {
        val links = eventsRepo.getCachedEvents()[position].links
        viewState.setText(links.linkReddit, links.linkArticle, links.linkWikipedia)
    }
}
