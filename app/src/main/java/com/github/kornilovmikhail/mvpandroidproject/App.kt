package com.github.kornilovmikhail.mvpandroidproject

import android.app.Application
import com.github.kornilovmikhail.mvpandroidproject.di.component.AppComponent
import com.github.kornilovmikhail.mvpandroidproject.di.component.DaggerAppComponent
import com.github.kornilovmikhail.mvpandroidproject.di.module.ContextModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        private var appComponent: AppComponent? = null

        fun getAppComponents(): AppComponent? = appComponent
    }
}
