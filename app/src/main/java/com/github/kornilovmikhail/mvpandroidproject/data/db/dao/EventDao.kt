package com.github.kornilovmikhail.mvpandroidproject.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import io.reactivex.Single

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(events: List<Event>)

    @Query("SELECT * FROM eventData")
    fun getEvents(): Single<List<Event>>

    @Query("DELETE FROM eventData")
    fun deleteEvents()
}
