package com.example.myfirstapplication.data.database.entities.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myfirstapplication.data.database.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY user_name DESC")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)
}