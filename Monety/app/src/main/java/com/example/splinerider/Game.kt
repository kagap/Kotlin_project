package com.example.splinerider

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import android.content.Intent
import android.os.CountDownTimer
import android.widget.RelativeLayout
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.content.ContentValues
import java.util.Date
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Game : AppCompatActivity(), View.OnTouchListener {
    private var clickedIcons: MutableSet<Int> = mutableSetOf()
    private lateinit var icon1_7: ImageView
    private lateinit var icon2_6: ImageView
    private lateinit var icon5_6: ImageView
    private lateinit var icon5_5: ImageView
    private lateinit var icon2_5: ImageView
    private lateinit var icon1_6: ImageView
    private lateinit var icon2_4: ImageView
    private lateinit var icon1_5: ImageView
    private lateinit var icon1_2: ImageView
    private lateinit var icon1_4: ImageView
    private lateinit var icon5_4: ImageView
    private lateinit var icon2_7: ImageView
    private lateinit var icon1_8: ImageView
    private lateinit var icon5_7: ImageView
    private lateinit var icon5_1: ImageView
    private lateinit var icon5_2: ImageView
    private lateinit var icon1_1: ImageView
    private lateinit var icon2_1: ImageView
    private lateinit var icon2_2: ImageView
    private lateinit var icon1_3: ImageView
    private lateinit var icon2_3: ImageView
    private lateinit var icon5_3: ImageView
    private lateinit var icon5_8: ImageView
    private lateinit var icon2_8: ImageView

    private var clickedIcons2: MutableSet<Int> = mutableSetOf()
    private var clickedIcons1: MutableSet<Int> = mutableSetOf()
    private var clickedIcons5: MutableSet<Int> = mutableSetOf()
    private lateinit var counterText: TextView
    private lateinit var timerText: TextView
    private lateinit var countDownTimer: CountDownTimer
    private var timeRemainingMillis: Long = 60000
    private var counter: Int = 0

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        timerText = findViewById(R.id.timerText)
        icon1_7 = findViewById(R.id.icon1_7)
        icon2_6 = findViewById(R.id.icon2_6)
        icon5_6 = findViewById(R.id.icon5_6)
        icon5_5 = findViewById(R.id.icon5_5)
        icon2_5 = findViewById(R.id.icon2_5)
        icon1_6 = findViewById(R.id.icon1_6)
        icon2_4 = findViewById(R.id.icon2_4)
        icon1_5 = findViewById(R.id.icon1_5)
        icon1_2 = findViewById(R.id.icon1_2)
        icon1_4 = findViewById(R.id.icon1_4)
        icon5_4 = findViewById(R.id.icon5_4)
        icon2_7 = findViewById(R.id.icon2_7)
        icon1_8 = findViewById(R.id.icon1_8)
        icon5_7 = findViewById(R.id.icon5_7)
        icon5_1 = findViewById(R.id.icon5_1)
        icon5_2 = findViewById(R.id.icon5_2)
        icon1_1 = findViewById(R.id.icon1_1)
        icon2_1 = findViewById(R.id.icon2_1)
        icon2_2 = findViewById(R.id.icon2_2)
        icon1_3 = findViewById(R.id.icon1_3)
        icon2_3 = findViewById(R.id.icon2_3)
        icon5_3 = findViewById(R.id.icon5_3)
        icon5_8 = findViewById(R.id.icon5_8)
        icon2_8 = findViewById(R.id.icon2_8)

        counterText = findViewById(R.id.counterText)

        icon1_7.setOnTouchListener(this)
        icon2_6.setOnTouchListener(this)
        icon5_6.setOnTouchListener(this)
        icon5_5.setOnTouchListener(this)
        icon2_5.setOnTouchListener(this)
        icon1_6.setOnTouchListener(this)
        icon2_4.setOnTouchListener(this)
        icon1_5.setOnTouchListener(this)
        icon1_2.setOnTouchListener(this)
        icon1_4.setOnTouchListener(this)
        icon5_4.setOnTouchListener(this)
        icon2_7.setOnTouchListener(this)
        icon1_8.setOnTouchListener(this)
        icon5_7.setOnTouchListener(this)
        icon5_1.setOnTouchListener(this)
        icon5_2.setOnTouchListener(this)
        icon1_1.setOnTouchListener(this)
        icon2_1.setOnTouchListener(this)
        icon2_2.setOnTouchListener(this)
        icon1_3.setOnTouchListener(this)
        icon2_3.setOnTouchListener(this)
        icon5_3.setOnTouchListener(this)
        icon5_8.setOnTouchListener(this)
        icon2_8.setOnTouchListener(this)

        databaseHelper = DatabaseHelper(this)

        startTimer()
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val iconId = view.id
                when (iconId) {
                    R.id.icon5_1,R.id.icon5_2,R.id.icon5_3,R.id.icon5_4,R.id.icon5_5,R.id.icon5_6,R.id.icon5_7,R.id.icon5_8 -> {
                        if (clickedIcons5.contains(iconId)) {
                            return true
                        }
                        clickedIcons5.add(iconId)
                    }
                    R.id.icon1_1,R.id.icon1_2,R.id.icon1_3,R.id.icon1_4,R.id.icon1_5,R.id.icon1_6,R.id.icon1_7,R.id.icon1_8-> {
                        if (clickedIcons1.contains(iconId)) {
                            return true
                        }
                        clickedIcons1.add(iconId)
                    }
                    R.id.icon2_1,R.id.icon2_2,R.id.icon2_3,R.id.icon2_4,R.id.icon2_5,R.id.icon2_6,R.id.icon2_7,R.id.icon2_8 -> {
                        if (clickedIcons2.contains(iconId)) {
                            return true
                        }
                        clickedIcons2.add(iconId)
                    }
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                val iconId = view.id
                when (iconId) {
                    R.id.icon5_1,R.id.icon5_2,R.id.icon5_3,R.id.icon5_4,R.id.icon5_5,R.id.icon5_6,R.id.icon5_7,R.id.icon5_8 -> {
                        if (clickedIcons5.size > 1 && clickedIcons5.contains(iconId)) {
                            incrementCounter(clickedIcons5.size*5)
                            clickedIcons5.forEach { id ->
                                val iconView = findViewById<View>(id)
                                iconView.visibility = View.GONE
                            }
                            clickedIcons5.clear()
                        }
                        clickedIcons5.remove(iconId)
                    }
                    R.id.icon1_1,R.id.icon1_2,R.id.icon1_3,R.id.icon1_4,R.id.icon1_5,R.id.icon1_6,R.id.icon1_7,R.id.icon1_8-> {
                        if (clickedIcons1.size > 1 && clickedIcons1.contains(iconId)) {
                            incrementCounter(clickedIcons1.size*1)
                            clickedIcons1.forEach { id ->
                                val iconView = findViewById<View>(id)
                                iconView.visibility = View.GONE
                            }
                            clickedIcons1.clear()
                        }
                        clickedIcons1.remove(iconId)
                    }
                    R.id.icon2_1,R.id.icon2_2,R.id.icon2_3,R.id.icon2_4,R.id.icon2_5,R.id.icon2_6,R.id.icon2_7 ,R.id.icon2_8-> {
                        if (clickedIcons2.size > 1 && clickedIcons2.contains(iconId)) {
                            incrementCounter(clickedIcons2.size*2)
                            clickedIcons2.forEach { id ->
                                val iconView = findViewById<View>(id)
                                iconView.visibility = View.GONE
                            }
                            clickedIcons2.clear()
                        }
                        clickedIcons2.remove(iconId)
                    }
                }
                checkGameEnd()
                return true
            }
        }
        return false
    }

    private fun checkGameEnd() {
        if (counter >= 64) {
            countDownTimer.cancel()
            showResultDialog()
        }
    }

    private fun showResultDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Wynik")
        builder.setMessage("Twój wynik: $counter")
        builder.setNegativeButton("Nowa gra") { dialog, which ->
            val intent = Intent(this, Game::class.java)
            startActivity(intent)
            finish()
        }
        builder.setPositiveButton("Zapisz") { dialog, which ->
            saveResultToDatabase()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun incrementCounter(value: Int) {
        counter += value
        counterText.text = counter.toString()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeRemainingMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemainingMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                timerText.text = "Czas upłynął"
                checkGameEnd()
            }
        }

        countDownTimer.start()
    }

    private fun updateTimerText() {
        val seconds = (timeRemainingMillis / 1000).toString()
        timerText.text = "Czas: $seconds s"
    }

    private fun saveResultToDatabase() {
        val username = "test"//loggedInUsername// Przykładowa nazwa użytkownika
        val result = counter
        val endTime = Date() // Pobierz aktualną datę i czas
        val countdown = timeRemainingMillis / 1000 // Przekształć wartość timeRemainingMillis na sekundy

        val db = databaseHelper.writableDatabase
        val values = ContentValues()
        values.put(DatabaseContract.ResultsEntry.COLUMN_USERNAME, username)
        values.put(DatabaseContract.ResultsEntry.COLUMN_RESULT, result)
        values.put(DatabaseContract.ResultsEntry.COLUMN_END_TIME, endTime.toString())
        values.put(DatabaseContract.ResultsEntry.COLUMN_COUNTDOWN, countdown)

        db.insert(DatabaseContract.ResultsEntry.TABLE_NAME, null, values)
        db.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
        databaseHelper.close()
    }
}
