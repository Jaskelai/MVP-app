package com.github.kornilovmikhail.mvpandroidproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.github.kornilovmikhail.mvpandroidproject.R
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(main_toolbar as Toolbar)

        val navController = findNavController(R.id.fragment_host)
        navigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.fragment_host).navigateUp()

    companion object {

        fun newIntent(context: Context): Intent = Intent(context, SecondActivity::class.java)

    }
}
