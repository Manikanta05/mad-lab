package com.example.authorizer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao    //Data Access object
interface UserDao {
    @Query("SELECT * FROM user WHERE username == :username")
    fun getUser(username: String): User
    @Insert
    fun insert(user: User)
}