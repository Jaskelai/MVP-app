package com.github.kornilovmikhail.mvpandroidproject.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.widget.ProgressBar
import android.widget.Toast
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.network.model.Event
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter.EventAdapter
import com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter.ListCallback

class MainActivity : AppCompatActivity(), MainViewInterface, ListCallback {

    private lateinit var rvEvents: RecyclerView
    private lateinit var mainPresenter: MainPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvEvents = rv_events
        progressBar = findViewById(R.id.progressBar)
        setupMVP()
        setupViews()
        getEventList()
    }

    override fun displayEvents(listEvents: List<Event>) {
        rvEvents.adapter = EventAdapter(listEvents)
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

    private fun setupMVP() {
        mainPresenter = MainPresenter(this)
    }

    private fun setupViews() {
        rvEvents.layoutManager = LinearLayoutManager(this)
    }

    private fun getEventList() {
        mainPresenter.getEvents()
    }
}
