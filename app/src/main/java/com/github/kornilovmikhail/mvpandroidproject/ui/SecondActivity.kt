package com.github.kornilovmikhail.mvpandroidproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.github.kornilovmikhail.mvpandroidproject.R
import kotlinx.android.synthetic.main.activity_second.*
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.kornilovmikhail.mvpandroidproject.ui.list.ListFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.nav_fragments.FirstFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.nav_fragments.FourthFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.nav_fragments.SecondFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.nav_fragments.ThirdFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.toolbar.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(main_toolbar as Toolbar?)
        replaceFragment(FirstFragment())

        val navController = findNavController(R.id.fragment_host)
        navigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            replaceFragment(
                when (destination.id) {
                    R.id.navigation_home -> SecondFragment()
                    R.id.navigation_search -> ThirdFragment()
                    R.id.navigation_feed -> FourthFragment()
                    else -> FirstFragment()
                }
            )

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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.fragment_host).navigateUp()
}