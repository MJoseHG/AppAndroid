package com.example.myfirstapplication.data.database.repositories

import com.example.myfirstapplication.data.database.AppDatabase
import com.example.myfirstapplication.data.database.entities.User

//Aqui tenemos la funcion executequery

class UserRepository : BaseDbRepository() {
    companion object {
        fun insert(user: User, database: AppDatabase) {
            executeQuery {
                database.userDao().insert(user)
            }
        }

        fun getUsers(database: AppDatabase): List<User>? {
            var users: List<User>? = null
            executeQuery {
                users = database.userDao().getAll()
            }
            //Hasta que no se ejectute esto de arriba no sigue y no vuelve al return
            return users
        }
    }
}