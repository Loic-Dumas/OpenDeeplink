package com.app.opendeeplink.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class DeeplinkEntity(
    @PrimaryKey
    @ColumnInfo(name = "deeplink") val deeplink: String,
    @ColumnInfo(name = "date") val date: Long = Date().time,
)