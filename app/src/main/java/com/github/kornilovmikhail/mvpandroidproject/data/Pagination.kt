package com.github.kornilovmikhail.mvpandroidproject.data

import android.content.SharedPreferences

object Pagination {
    private const val DEFAULT_PAGINATION = 12
    private const val NAME_PAGINATION = "current_pagination"
    private lateinit var preferences: SharedPreferences

    fun setSharedPrefs(preferences: SharedPreferences) {
        this.preferences = preferences
        preferences.edit().putInt(NAME_PAGINATION, DEFAULT_PAGINATION).apply()
    }

    fun setCurrentPagination(pagination: Int) {
        preferences.edit().putInt(NAME_PAGINATION, pagination).apply()
    }

    fun getCurrentPagination(): Int {
        val value = 0
        preferences.getInt(NAME_PAGINATION, value)
        return value
    }

}