package com.example.letsconnect

class FriendsRepository(private val db:FriendsDatabase) {
    suspend fun insert(items: FriendsItems)=db.getFriendsDAO().insert(items)
    suspend fun delete(items: FriendsItems)=db.getFriendsDAO().delete(items)
    suspend fun update(items: FriendsItems)=db.getFriendsDAO().update(items)

    fun getAllItems()=db.getFriendsDAO().getAllVaccineItems()
}