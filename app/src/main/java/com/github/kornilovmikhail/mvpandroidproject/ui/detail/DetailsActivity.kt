package com.github.kornilovmikhail.mvpandroidproject.ui.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kornilovmikhail.mvpandroidproject.App
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.di.event.component.DaggerEventComponent
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.EventModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.PresenterModule
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.MainActivity
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
            .eventModule(EventModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupViews()
        position = intent.getIntExtra(MainActivity.EXTRA_POSITION, 0)
        detailPresenter.getEvent(position)
    }

    private fun setupViews() {
        setSupportActionBar(detail_toolbar as Toolbar?)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_details, menu)
        return true
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

    override fun displayEvent(event: Event) {
        tv_title_details_activity.text = event.title
        tv_details_details_activity.text = event.details
        tv_event_date_details_activity.text = event.eventDate.toString()
    }

    override fun displayError() {
        Toast.makeText(this, getString(R.string.server_events_error), Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        details_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        details_progressBar.visibility = ProgressBar.INVISIBLE
    }
}
