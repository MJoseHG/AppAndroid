package com.example.myfirstapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myfirstapplication.R
import com.google.android.material.appbar.MaterialToolbar


class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        supportActionBar?.hide()

        val topAppBarReturn = findViewById<MaterialToolbar>(R.id.topAppBarReturn) as Toolbar

        topAppBarReturn.setNavigationOnClickListener {
            finish()
        }

    }
}