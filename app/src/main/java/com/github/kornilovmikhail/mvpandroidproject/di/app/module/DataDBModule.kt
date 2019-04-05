package com.github.kornilovmikhail.mvpandroidproject.di.app.module

import androidx.room.Room
import android.content.Context
import com.github.kornilovmikhail.mvpandroidproject.data.local.EventDatabase
import com.github.kornilovmikhail.mvpandroidproject.data.local.dao.EventDao
import com.github.kornilovmikhail.mvpandroidproject.di.app.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DataDBModule {
    companion object {
        const val DATABASE_NAME = "spaceX_app.db"
    }

    @Provides
    @ApplicationScope
    fun provideEventDatabase(context: Context): EventDatabase = Room.databaseBuilder(
        context,
        EventDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @ApplicationScope
    fun provideEventDao(eventDatabase: EventDatabase): EventDao = eventDatabase.eventDao()
}
