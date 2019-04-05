package com.github.kornilovmikhail.mvpandroidproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.kornilovmikhail.mvpandroidproject.R
import kotlinx.android.synthetic.main.activity_second.*
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.toolbar.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val navController = findNavController(R.id.fragment_host)
        navigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            toolbar.setTitle(
                when (destination.id) {
                    R.id.navigation_home -> R.string.title_home
                    R.id.navigation_search -> R.string.title_search
                    R.id.navigation_feed -> R.string.title_feed
                    else -> R.string.app_name
                }
            )
        }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.fragment_host).navigateUp()
}