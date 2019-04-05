package com.github.kornilovmikhail.mvpandroidproject.ui.nav_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.kornilovmikhail.mvpandroidproject.R
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : MvpAppCompatFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_second, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        button_second.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.sixthFragment, null))
    }
}
