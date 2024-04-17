package com.app.opendeeplink.domain.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.opendeeplink.data.DeeplinkEntity

@Dao
interface DeeplinkHistoryDao {

    @Query("SELECT * FROM deeplinkentity ORDER BY date DESC")
    fun getAll(): List<DeeplinkEntity>

    @Query("SELECT * FROM deeplinkentity ORDER BY date DESC")
    fun getAllLiveData(): LiveData<List<DeeplinkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(deeplink: DeeplinkEntity)

    @Query("DELETE FROM deeplinkentity WHERE deeplink = :link")
    fun delete(link: String)

    @Query("DELETE FROM deeplinkentity")
    fun deleteAll()

}