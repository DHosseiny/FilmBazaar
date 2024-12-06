package com.example.filmbazzar.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKeyEntity(
    @PrimaryKey
    val id: String,
    val page: Int?,
    @ColumnInfo("total_page")
    val totalPage: Int,
)