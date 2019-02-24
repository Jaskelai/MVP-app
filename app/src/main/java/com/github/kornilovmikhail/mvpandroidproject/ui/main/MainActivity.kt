package com.github.kornilovmikhail.mvpandroidproject.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kornilovmikhail.mvpandroidproject.R

class MainActivity : AppCompatActivity(), MainViewInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
