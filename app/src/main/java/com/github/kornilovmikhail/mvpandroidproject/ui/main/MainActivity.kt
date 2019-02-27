package com.github.kornilovmikhail.mvpandroidproject.ui.main

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.network.response.Event
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter.EventAdapter


class MainActivity : MvpActivity(), MainView {

    private lateinit var eventsAdapter: EventAdapter
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        getEventList()
//        rv_events.addOnScrollListener(rvOnScrollListener)
    }

//    private val rvOnScrollListener = object : RecyclerView.OnScrollListener() {
//
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            super.onScrolled(recyclerView, dx, dy)
//            val visibleItemCount = recyclerView.layoutManager?.childCount
//            val totalItemCount = recyclerView.layoutManager?.itemCount
//            val firstVisibleItemPosition = recyclerView.layoutManager?.
//        }
//    }

    override fun displayEvents(listEvents: List<Event>) {
        eventsAdapter = EventAdapter(listEvents) {
            mainPresenter.eventClick(it)
        }
        rv_events.adapter = eventsAdapter
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

    private fun getEventList() {
        mainPresenter.getEvents()
    }
}
