package com.github.kornilovmikhail.mvpandroidproject.ui

import androidx.fragment.app.Fragment
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.list.ListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ListScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = ListFragment.getInstance()
    }

    class DetailScreen(private val position: Int) : SupportAppScreen() {
        override fun getFragment(): Fragment = DetailsFragment.getInstance(position)
    }

    class LinksScreen(private val position: Int) : SupportAppScreen() {
        override fun getFragment(): Fragment = LinksFragment.getInstance(position)
    }
}
