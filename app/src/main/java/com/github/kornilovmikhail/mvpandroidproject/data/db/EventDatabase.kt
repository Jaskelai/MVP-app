package com.github.kornilovmikhail.mvpandroidproject.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.github.kornilovmikhail.mvpandroidproject.data.db.dao.EventDao
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Links

@Database(entities = [Event::class, Links::class], version = 1)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        private const val DATABASE_NAME = "spaceX_app.db"
        private var instance: EventDatabase? = null

        fun getInstance(context: Context): EventDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    EventDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
            }
            return instance as EventDatabase
        }
    }
}