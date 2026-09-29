package com.example.splinerider

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (login(username, password)) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Błędne dane logowania",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (register(username, password)) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Rejestracja udana",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Nazwa użytkownika już istnieje",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        usernameEditText.doOnTextChanged { _, _, _, _ -> updateButtonsState() }
        passwordEditText.doOnTextChanged { _, _, _, _ -> updateButtonsState() }
        updateButtonsState()
    }

    private fun updateButtonsState() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        loginButton.isEnabled = username.isNotEmpty() && password.isNotEmpty()
        registerButton.isEnabled = username.isNotEmpty() && password.isNotEmpty()
    }

    private fun register(username: String, password: String): Boolean {
        val db = databaseHelper.writableDatabase
        val existing = db.query(
            UserContract.UserEntry.TABLE_NAME,
            arrayOf(UserContract.UserEntry.COLUMN_USERNAME),
            "${UserContract.UserEntry.COLUMN_USERNAME} = ?",
            arrayOf(username),
            null, null, null
        )
        val alreadyRegistered = existing.moveToFirst()
        existing.close()
        if (alreadyRegistered) {
            db.close()
            return false
        }

        val values = ContentValues().apply {
            put(UserContract.UserEntry.COLUMN_USERNAME, username)
            put(UserContract.UserEntry.COLUMN_PASSWORD, password)
        }
        db.insert(UserContract.UserEntry.TABLE_NAME, null, values)
        db.close()
        return true
    }

    private fun login(username: String, password: String): Boolean {
        val db = databaseHelper.readableDatabase
        val cursor = db.query(
            UserContract.UserEntry.TABLE_NAME,
            arrayOf(UserContract.UserEntry.COLUMN_USERNAME),
            "${UserContract.UserEntry.COLUMN_USERNAME} = ? AND ${UserContract.UserEntry.COLUMN_PASSWORD} = ?",
            arrayOf(username, password),
            null, null, null
        )
        val found = cursor.moveToFirst()
        cursor.close()
        db.close()
        return found
    }
}
