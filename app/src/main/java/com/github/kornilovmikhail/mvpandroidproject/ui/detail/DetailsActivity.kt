package com.github.kornilovmikhail.mvpandroidproject.ui.detail

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.github.kornilovmikhail.mvpandroidproject.R
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailsActivity : MvpAppCompatActivity(), DetailsView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupViews()
    }

    private fun setupViews(){
        setSupportActionBar(main_toolbar as Toolbar?)
        tv_title_details_activity.text = intent.getStringExtra("title")
        tv_details_details_activity.text = intent.getStringExtra("details")
        tv_event_date_details_activity.text = intent.getStringExtra("eventDate")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_details, menu)
        return true
    }

    //
    //
    //нужны аргументы в метод navigateToLinks(.....)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.github.kornilovmikhail.mvpandroidproject.R.id.action_open_links -> navigateToLinks()
        }
        return true
    }

    override fun navigateToLinks(reddit: String?, article: String?, wiki:String?) {
        val intent = Intent(this@DetailsActivity, DetailsActivity::class.java)
        intent.putExtra("reddit", reddit)
        intent.putExtra("article", article)
        intent.putExtra("wiki", wiki)
        startActivity(intent)
    }
}
