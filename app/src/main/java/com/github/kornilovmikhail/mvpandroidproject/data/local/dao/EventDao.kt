package com.github.kornilovmikhail.mvpandroidproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<Event>)

    @Query("SELECT * FROM eventData")
    suspend fun getEvents(): List<Event>

    @Query("DELETE FROM eventData")
    suspend fun deleteEvents()
}
