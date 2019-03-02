package com.github.kornilovmikhail.mvpandroidproject.ui.main

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.network.response.Event
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter.EventAdapter
import com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter.OnScrollListener


class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter
    private lateinit var onScrollListener: OnScrollListener
    private lateinit var eventsAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setSupportActionBar(main_toolbar as Toolbar?)
        mainPresenter.getEvents(0)
    }

    override fun displayEvents(listEvents: List<Event>) {
        eventsAdapter = EventAdapter(listEvents) {
            mainPresenter.eventClick(it)
        }
        rv_events.adapter = eventsAdapter
        onScrollListener = OnScrollListener(
            rv_events.layoutManager as LinearLayoutManager
        ) {
            mainPresenter.getEvents(it)
            println("GETEVENTS $it")
        }
        rv_events.addOnScrollListener(onScrollListener)
        Toast.makeText(this, getString(R.string.server_events_success), Toast.LENGTH_SHORT).show()
    }

    override fun displayError() {
        Toast.makeText(this, getString(R.string.server_events_error), Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    override fun detachOnScrollListeners() {
        rv_events.removeOnScrollListener(onScrollListener)
    }

    override fun navigateToMain(title: String, details: String, eventDate: String) {
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("details", details)
        intent.putExtra("eventDate", eventDate)
        startActivity(intent)
    }

    private fun setupViews() {
        rv_events.layoutManager = LinearLayoutManager(this)
    }
}
