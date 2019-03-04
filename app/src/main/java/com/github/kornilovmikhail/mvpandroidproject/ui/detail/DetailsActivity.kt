package com.github.kornilovmikhail.mvpandroidproject.ui.detail

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : MvpAppCompatActivity(), DetailView {

    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupViews()
    }

    private fun setupViews() {
        detailPresenter.getEvent(intent.getIntExtra("position", 0))
    }

    override fun setText(title: String, details: String, date: String) {
        tv_title_details_activity.text = title
            tv_details_details_activity.text = details
        tv_event_date_details_activity.text = date
    }
}
