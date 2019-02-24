package com.github.kornilovmikhail.mvpandroidproject.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.kornilovmikhail.mvpandroidproject.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsViewInterface{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupViews()
    }

    private fun setupViews(){
        tv_title_details_activity.text = intent.getStringExtra("title")
        tv_details_details_activity.text = intent.getStringExtra("details")
        tv_event_date_details_activity.text = intent.getStringExtra("eventDate")
        displaySuccess()
    }

    override fun displaySuccess() {
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show()
    }
}
