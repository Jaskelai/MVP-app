package com.github.kornilovmikhail.mvpandroidproject.ui.links

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.presenter.LinksPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsActivity
import kotlinx.android.synthetic.main.activity_links.*

class LinksActivity : MvpAppCompatActivity(), LinksView {

    @InjectPresenter
    lateinit var linksPresenter: LinksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_links)
        setupViews()
    }

    private fun setupViews() {
        linksPresenter.getLinks(intent.getIntExtra(DetailsActivity.EXTRA_POSITION, 0))
    }

    override fun setText(article: String?, reddit: String?, wikipedia: String?) {
        tv_link_article.text = article ?: getString(R.string.none)
        tv_link_reddit.text = reddit ?: getString(R.string.none)
        tv_link_wiki.text = wikipedia ?: getString(R.string.none)
    }
}
