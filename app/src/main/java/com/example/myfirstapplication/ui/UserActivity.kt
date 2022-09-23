package com.example.myfirstapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapplication.R

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        supportActionBar?.hide()

//        val arrowReturn =findViewById<>(R.id.topAppBarReturn)
//        arrowReturn.setOnClickListener{
//            finish()
//        }
    }
}