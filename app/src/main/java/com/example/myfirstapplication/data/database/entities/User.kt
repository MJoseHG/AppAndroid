package com.example.myfirstapplication.data.database.entities

import androidx.room.*

@Entity (tableName = "user_table")
data class User(
    @PrimaryKey (autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "password_name") val passwordName: String?

)