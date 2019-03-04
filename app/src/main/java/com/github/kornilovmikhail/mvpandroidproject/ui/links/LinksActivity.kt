package com.github.kornilovmikhail.mvpandroidproject.ui.links

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.presenter.LinksPresenter
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
        linksPresenter.getLinks(intent.getIntExtra("position", 0))
    }

    override fun setText(article: String?, reddit: String?, wikipedia: String?) {
        if (article == null) {
            tv_link_article.text = getString(R.string.none)
        } else {
            tv_link_article.text = article
        }
        if (reddit == null) {
            tv_link_reddit.text = getString(R.string.none)
        } else {
            tv_link_reddit.text = reddit
        }
        if (wikipedia == null) {
            tv_link_wiki.text = getString(R.string.none)
        } else {
            tv_link_wiki.text = wikipedia
        }
    }
}
