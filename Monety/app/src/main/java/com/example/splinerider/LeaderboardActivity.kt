package com.example.splinerider

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class LeaderboardEntry(val username: String, val result: Int)

class LeaderboardActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        databaseHelper = DatabaseHelper(this)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LeaderboardAdapter(loadResults())

        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadResults(): List<LeaderboardEntry> {
        val db = databaseHelper.readableDatabase
        val cursor = db.query(
            DatabaseContract.ResultsEntry.TABLE_NAME,
            arrayOf(DatabaseContract.ResultsEntry.COLUMN_USERNAME, DatabaseContract.ResultsEntry.COLUMN_RESULT),
            null, null, null, null,
            "${DatabaseContract.ResultsEntry.COLUMN_RESULT} DESC"
        )

        val usernameIndex = cursor.getColumnIndexOrThrow(DatabaseContract.ResultsEntry.COLUMN_USERNAME)
        val resultIndex = cursor.getColumnIndexOrThrow(DatabaseContract.ResultsEntry.COLUMN_RESULT)
        val entries = mutableListOf<LeaderboardEntry>()
        while (cursor.moveToNext()) {
            entries.add(LeaderboardEntry(cursor.getString(usernameIndex), cursor.getInt(resultIndex)))
        }
        cursor.close()
        db.close()
        return entries
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseHelper.close()
    }
}
