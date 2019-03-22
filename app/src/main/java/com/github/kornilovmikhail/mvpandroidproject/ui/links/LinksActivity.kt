package com.github.kornilovmikhail.mvpandroidproject.ui.links

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kornilovmikhail.mvpandroidproject.App
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.di.component.DaggerEventComponent
import com.github.kornilovmikhail.mvpandroidproject.di.module.*
import com.github.kornilovmikhail.mvpandroidproject.presenter.LinksPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsActivity
import kotlinx.android.synthetic.main.activity_links.*
import javax.inject.Inject

class LinksActivity : MvpAppCompatActivity(), LinksView {
    @Inject
    @InjectPresenter
    lateinit var linksPresenter: LinksPresenter

    private var position = 0

    @ProvidePresenter
    fun getPresenter(): LinksPresenter = linksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerEventComponent.builder()
            .appComponent(App.getAppComponents())
            .baseModule(BaseModule())
            .linksModule(LinksModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_links)
        position = intent.getIntExtra(DetailsActivity.EXTRA_POSITION, 0)
    }

    override fun displayEvent(event: Event) {
        tv_link_article.text = event.links.linkArticle ?: getString(R.string.none)
        tv_link_reddit.text = event.links.linkReddit ?: getString(R.string.none)
        tv_link_wiki.text = event.links.linkWikipedia ?: getString(R.string.none)
    }

    override fun displayError() {
        Toast.makeText(this, getString(R.string.server_events_error), Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        links_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        links_progressBar.visibility = ProgressBar.INVISIBLE
    }
}
