package com.example.splinerider

import android.os.Bundle

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LeaderboardActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
        onBackPressed()
    }

}
}
