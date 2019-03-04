package com.github.kornilovmikhail.mvpandroidproject.ui.links

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.github.kornilovmikhail.mvpandroidproject.R
import kotlinx.android.synthetic.main.activity_links.*

class LinksActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_links)
        setupViews()
    }

    private fun setupViews(){
        tv_link_reddit.text = intent.getStringExtra("reddit")
        tv_link_article.text = intent.getStringExtra("article")
        tv_link_wiki.text = intent.getStringExtra("wiki")
    }
}
