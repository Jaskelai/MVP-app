package com.github.kornilovmikhail.mvpandroidproject.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.github.kornilovmikhail.mvpandroidproject.data.db.EventDatabase
import com.github.kornilovmikhail.mvpandroidproject.data.db.dao.EventDao
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsDBRepo
import com.github.kornilovmikhail.mvpandroidproject.di.scope.EventScope
import dagger.Module
import dagger.Provides

@Module
class DBModule {
    @Provides
    @EventScope
    fun provideEventDatabase(context: Context): EventDatabase = Room.databaseBuilder(
        context,
        EventDatabase::class.java,
        EventDatabase.DATABASE_NAME
    ).build()

    @Provides
    @EventScope
    fun provideEventDao(eventDatabase: EventDatabase): EventDao = eventDatabase.eventDao()

    @Provides
    @EventScope
    fun provideEventsDBRepo(eventDao: EventDao): EventsDBRepo = EventsDBRepo(eventDao)
}
