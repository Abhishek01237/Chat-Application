package com.example.letsconnect

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FriendsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FriendsItems)

    @Delete
    suspend fun delete(item:FriendsItems)

    @Update
    suspend fun update(item: FriendsItems)

    @Query("Select *FROM friends_items ")

    fun getAllVaccineItems(): LiveData<List<FriendsItems>>
}