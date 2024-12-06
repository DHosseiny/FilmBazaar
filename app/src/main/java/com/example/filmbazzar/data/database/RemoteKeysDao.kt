package com.example.filmbazzar.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmbazzar.data.database.model.RemoteKeyEntity

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(remoteKey: RemoteKeyEntity)

    @Query("Select * From remote_key WHERE id = :id")
    suspend fun getLastRemoteKey(id: String): RemoteKeyEntity?

    @Query("Delete From remote_key")
    suspend fun clearRemoteKeys()
}