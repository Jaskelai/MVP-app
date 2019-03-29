package com.github.kornilovmikhail.mvpandroidproject.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.github.kornilovmikhail.mvpandroidproject.data.db.dao.EventDao
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Links

@Database(entities = [Event::class, Links::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
