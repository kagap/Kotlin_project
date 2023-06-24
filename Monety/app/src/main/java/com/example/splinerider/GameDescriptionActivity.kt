package com.example.splinerider

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GameDescriptionActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_description)

        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressed()
        }

    }
}
