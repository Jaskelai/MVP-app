package com.github.kornilovmikhail.mvpandroidproject.ui.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksActivity
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailsActivity : MvpAppCompatActivity(), DetailView {

    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(detail_toolbar as Toolbar?)
        position = intent.getIntExtra("position", 0)
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
        intent.putExtra("position", position)
        startActivity(intent)
    }
}

