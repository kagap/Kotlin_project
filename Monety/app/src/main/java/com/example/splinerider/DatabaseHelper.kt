package com.example.splinerider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.splinerider.UserContract.UserEntry

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "game_results3.db"
        const val DATABASE_VERSION = 2
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DatabaseContract.ResultsEntry.SQL_CREATE_TABLE)
        db.execSQL(UserEntry.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DatabaseContract.ResultsEntry.SQL_DELETE_TABLE)
        db.execSQL(UserEntry.SQL_DELETE_TABLE)
        onCreate(db)
    }
}
