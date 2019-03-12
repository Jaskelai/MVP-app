package com.github.kornilovmikhail.mvpandroidproject.data

import android.content.SharedPreferences

object Pagination {

    private const val DEFAULT_PAGINATION = 12
    private const val NAME_PAGINATION = "current_pagination"
    private var preference: SharedPreferences? = null

    fun setSharedPrefs(preferences: SharedPreferences) {
        preference = preferences
        preferences.edit().putInt(NAME_PAGINATION, DEFAULT_PAGINATION).apply()
    }

    fun setCurrentPagination(pagination: Int) {
        preference?.edit()?.putInt(NAME_PAGINATION, pagination)?.apply()
    }

    fun getCurrentPagination(): Int? = preference?.getInt(NAME_PAGINATION, DEFAULT_PAGINATION)
}
