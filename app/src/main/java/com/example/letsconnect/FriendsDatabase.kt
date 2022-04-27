package com.example.letsconnect

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [FriendsItems::class],version = 1)
abstract class FriendsDatabase:RoomDatabase() {

    abstract fun getFriendsDAO():FriendsDao
    companion object{

        @Volatile
        private var INSTANCE:FriendsDatabase?=null

        fun getDatabase(context: Context):FriendsDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FriendsDatabase::class.java,
                    "friends_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}