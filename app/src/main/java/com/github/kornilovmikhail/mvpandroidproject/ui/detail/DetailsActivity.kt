package com.github.kornilovmikhail.mvpandroidproject.ui.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kornilovmikhail.mvpandroidproject.App
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.di.component.DaggerEventComponent
import com.github.kornilovmikhail.mvpandroidproject.di.module.*
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : MvpAppCompatActivity(), DetailView {

    @Inject
    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter

    @ProvidePresenter
    fun getPresenter(): DetailPresenter = detailPresenter

    private var position: Int = 0

    companion object {
        const val EXTRA_POSITION: String = "position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerEventComponent.builder()
            .appComponent(App.getAppComponents())
            .detailModule(DetailModule())
            .baseModule(BaseModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(detail_toolbar as Toolbar?)
        position = intent.getIntExtra(MainActivity.EXTRA_POSITION, 0)
        detailPresenter.getEvent(position)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_details, menu)
        return true
    }

    override fun setText(title: String, details: String, date: String) {
        tv_title_details_activity.text = title
        tv_details_details_activity.text = details
        tv_event_date_details_activity.text = date
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_open_links -> navigateToLinks(position)
        }
        return true
    }

    private fun navigateToLinks(position: Int) {
        val intent = Intent(this@DetailsActivity, LinksActivity::class.java)
        intent.putExtra(EXTRA_POSITION, position)
        startActivity(intent)
    }
}

