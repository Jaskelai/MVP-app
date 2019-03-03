package com.github.kornilovmikhail.mvpandroidproject

import android.app.Application
import android.content.Context


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MyApplication.appContext = applicationContext
    }

    companion object {

        lateinit var appContext: Context
            private set
    }
}