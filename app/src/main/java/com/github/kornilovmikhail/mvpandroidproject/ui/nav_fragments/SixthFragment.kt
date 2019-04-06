package com.github.kornilovmikhail.mvpandroidproject.ui.nav_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.arellomobile.mvp.MvpAppCompatFragment
import com.github.kornilovmikhail.mvpandroidproject.R
import kotlinx.android.synthetic.main.fragment_sixth.*

class SixthFragment : MvpAppCompatFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_sixth, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_sixth.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.firstFragment, null))
    }
}