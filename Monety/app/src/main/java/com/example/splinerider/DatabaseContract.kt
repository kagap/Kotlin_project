package com.example.splinerider

import android.provider.BaseColumns

object DatabaseContract {

    object ResultsEntry : BaseColumns {
        const val TABLE_NAME = "results"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_RESULT = "result"
        const val COLUMN_END_TIME="endTime"
        const val COLUMN_COUNTDOWN="countdown "

        const val SQL_CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "$COLUMN_USERNAME TEXT," +
                    "$COLUMN_RESULT INTEGER," +
                    "$COLUMN_END_TIME TEXT," +
                    "$COLUMN_COUNTDOWN INTEGER)"


        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}
