package com.app.opendeeplink.domain.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.opendeeplink.data.DeeplinkEntity

@Database(entities = [DeeplinkEntity::class], version = 1)
abstract class DeeplinkDatabase : RoomDatabase() {

    abstract fun userDao(): DeeplinkHistoryDao

    companion object {
        private const val DATA_BASE_NAME = "deeplink_data_base"
        private const val TAG = "PlayDB"

        fun createDataBase(context: Context): DeeplinkDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = DeeplinkDatabase::class.java,
                name = DATA_BASE_NAME
            ).build()
        }
    }
}