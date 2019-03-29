package com.github.kornilovmikhail.mvpandroidproject

import android.app.Application
import com.github.kornilovmikhail.mvpandroidproject.di.app.component.AppComponent
import com.github.kornilovmikhail.mvpandroidproject.di.app.component.DaggerAppComponent
import com.github.kornilovmikhail.mvpandroidproject.di.app.module.ContextModule
import com.github.kornilovmikhail.mvpandroidproject.di.app.module.DataDBModule
import com.github.kornilovmikhail.mvpandroidproject.di.app.module.DataNetModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .dataDBModule(DataDBModule())
            .dataNetModule(DataNetModule())
            .build()
    }

    companion object {
        private var appComponent: AppComponent? = null

        fun getAppComponents(): AppComponent? = appComponent
    }
}
