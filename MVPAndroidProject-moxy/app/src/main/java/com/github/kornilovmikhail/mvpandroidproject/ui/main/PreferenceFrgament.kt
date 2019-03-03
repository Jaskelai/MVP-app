package com.github.kornilovmikhail.mvpandroidproject.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat

open class PreferenceFragment : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedState: Bundle?, key: String?) {
        setPreferencesFromResource(R.xml.prefs_screen.xml,key)
    }
}
