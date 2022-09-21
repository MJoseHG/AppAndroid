package com.example.myfirstapplication.data.database.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

//Clase abstracta para que todos tus repos tengan esta funcionalidad
abstract class BaseDbRepository {
    companion object {
        //que es lo que hace
        //Inicia un Unit es decir, una tarea
        //Es un bloque porque bloquea que no siga el código
        fun executeQuery(block: () -> Unit) {
            //Corre la corrutina
            runBlocking {
                //Selecciona que tipo de corrutina, este caso la Dispatchers.Default
                //IO INPUT OUPUT, ES DECIR INTERFAZ
                //Main, hilo principal. Esta no la usamos porque lo que falla es el hilo principal no?
                //Esa no se cual es y no la uso nunca
                //Ponemos por defecto
                withContext(Dispatchers.Default) {
                    //Ejecuta block para que no siga corriendo el código hasta que no acabe esto
                    block()
                }
            }
        }
    }
}