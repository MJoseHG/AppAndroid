package com.example.myfirstapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstapplication.data.database.entities.User
import com.example.myfirstapplication.data.database.entities.dao.UserDao
import java.time.chrono.HijrahChronology.INSTANCE

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            //Haz que devuelva esto si no es null, como lo tenías antes
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database-login"
                )
                    .build()
            }
            return INSTANCE
        }
    }
}