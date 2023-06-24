package com.example.splinerider

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var btnNewGame: Button
    private lateinit var btnBestScores: Button
    private lateinit var btnLogout: Button
    private lateinit var btnGameDescription: Button
    private lateinit var btnAuthorInfo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnNewGame = findViewById(R.id.btnNewGame)
        btnBestScores = findViewById(R.id.btnBestScores)
        btnLogout = findViewById(R.id.btnLogout)
        btnGameDescription = findViewById(R.id.btnGameDescription)
        btnAuthorInfo = findViewById(R.id.btnAuthorInfo)

       btnNewGame.setOnClickListener {
           val intent = Intent(this, Game::class.java)
           startActivity(intent)
        }

       /*btnBestScores.setOnClickListener {
                  val intent = Intent(this, LeaderboardActivity::class.java)
                  startActivity(intent)
               }
*/
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnGameDescription.setOnClickListener {
            val intent = Intent(this, GameDescriptionActivity::class.java)
            startActivity(intent)
        }

        btnAuthorInfo.setOnClickListener {
            val intent = Intent(this,Autor::class.java)
            startActivity(intent)
        }
    }
}