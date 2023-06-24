package com.example.splinerider

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Autor : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.autor_activity)

        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressed()
        }

    }
}