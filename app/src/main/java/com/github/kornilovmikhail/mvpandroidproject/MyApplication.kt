package com.github.kornilovmikhail.mvpandroidproject

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MyApplication.appContext = applicationContext
    }

    companion object {
        var appContext: Context by Delegates.notNull()
            private set
    }
}
