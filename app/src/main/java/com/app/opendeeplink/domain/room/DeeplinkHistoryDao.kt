package com.app.opendeeplink.domain.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.opendeeplink.data.DeeplinkEntity

@Dao
interface DeeplinkHistoryDao {

    @Query("SELECT * FROM deeplinkentity ORDER BY date")
    fun getAll(): List<DeeplinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg deeplink: DeeplinkEntity)

    @Query("DELETE FROM deeplinkentity WHERE deeplink = :link")
    fun delete(link: String)

    @Query("DELETE FROM deeplinkentity")
    fun deleteAll()

}