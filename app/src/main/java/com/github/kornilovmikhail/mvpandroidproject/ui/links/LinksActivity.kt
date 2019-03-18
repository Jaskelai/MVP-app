package com.github.kornilovmikhail.mvpandroidproject.ui.links

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kornilovmikhail.mvpandroidproject.App
import com.github.kornilovmikhail.mvpandroidproject.R
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
