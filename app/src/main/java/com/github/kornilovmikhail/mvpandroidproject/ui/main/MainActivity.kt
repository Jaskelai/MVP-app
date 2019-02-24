package com.github.kornilovmikhail.mvpandroidproject.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
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
    private lateinit var listEvents: List<Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvEvents = rv_events
        setupMVP()
        setupViews()
        getEventList()
    }

    override fun displayEvents(listEvents: List<Event>) {
        this.listEvents = listEvents
        rvEvents.adapter = EventAdapter(listEvents)
        Toast.makeText(this, getString(R.string.server_events_success), Toast.LENGTH_SHORT).show()
    }

    override fun displayError() {
        Toast.makeText(this, getString(R.string.server_events_error), Toast.LENGTH_SHORT).show()
    }

    override fun callback(position: Int) {
        println(position)
        println(listEvents)
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("title", listEvents[position].title)
        intent.putExtra("details", listEvents[position].details)
        intent.putExtra("eventDate", listEvents[position].eventDate)
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
