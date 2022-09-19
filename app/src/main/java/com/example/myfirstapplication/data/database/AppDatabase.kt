package com.example.myfirstapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfirstapplication.data.database.entities.User
import com.example.myfirstapplication.data.database.entities.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}