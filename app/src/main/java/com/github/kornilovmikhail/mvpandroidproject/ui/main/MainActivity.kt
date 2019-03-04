package com.github.kornilovmikhail.mvpandroidproject.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter.EventAdapter
import android.widget.EditText
import android.support.v7.app.AlertDialog
import android.widget.Button
import com.github.kornilovmikhail.mvpandroidproject.R
import kotlinx.android.synthetic.main.pagination_dialog.*


class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter
    private var eventsAdapter: EventAdapter? = null
    private val nameSharedprefs: String = "Pagination"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        mainPresenter.getEvents(0)
        mainPresenter.initSharedPrefs(getSharedPreferences(nameSharedprefs, Context.MODE_PRIVATE))
    }

    private fun setupViews() {
        rv_events.layoutManager = LinearLayoutManager(this)
        rv_events.addOnScrollListener(OnScrollListener(
            rv_events.layoutManager as LinearLayoutManager
        ) {
            mainPresenter.getEvents(it)
        })
        setSupportActionBar(main_toolbar as Toolbar?)
    }

    override fun displayEvents(listEvents: List<Event>) {
        if (eventsAdapter == null) {
            eventsAdapter = EventAdapter(listEvents) {
                mainPresenter.eventClick(it)
            }
            rv_events.adapter = eventsAdapter
        }
        eventsAdapter?.submitList(listEvents)
    }

    override fun displaySuccess() {
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
        rv_events.clearOnScrollListeners()
    }

    override fun navigateToMain(position: Int) {
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.github.kornilovmikhail.mvpandroidproject.R.id.action_pagination -> createDialog()
        }
        return true
    }

    private fun createDialog() {
        val dialogBuilder = AlertDialog.Builder(this).create()
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.pagination_dialog, null)

        val editText = edt_pagination as EditText
        val btnSubmit = btn_Submit as Button
        val button2 = btn_Cancel as Button

        button2.setOnClickListener { dialogBuilder.dismiss() }
        btnSubmit.setOnClickListener {
            mainPresenter.setSharedPrefs(editText.text.toString().toInt())
            dialogBuilder.dismiss()
        }

        dialogBuilder.setView(dialogView)
        dialogBuilder.show()
    }
}