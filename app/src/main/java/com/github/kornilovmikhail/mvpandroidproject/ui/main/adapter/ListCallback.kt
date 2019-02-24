package com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter

interface ListCallback {
    fun navigateToMain(title: String, details: String, eventDate: String)
}
