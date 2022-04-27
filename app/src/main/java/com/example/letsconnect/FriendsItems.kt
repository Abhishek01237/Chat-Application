package com.example.letsconnect

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


@Entity(tableName="friends_items")
data class FriendsItems(
    @ColumnInfo(name = "friendName")
    var friendName:String,

    @ColumnInfo(name = "mobileNumber")
    var mobileNumber:String,

    @ColumnInfo(name = "emailId")
    var emailId:Int,

    @ColumnInfo(name = "Note")
    var note:String

){

    @PrimaryKey(autoGenerate = true)
    var id:Int?= null
}
