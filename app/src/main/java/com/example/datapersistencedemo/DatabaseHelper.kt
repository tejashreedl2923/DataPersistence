
package com.example.datapersistencedemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "NotesDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE notes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT NOT NULL, " +
                    "content TEXT NOT NULL, " +
                    "date TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS notes")
        onCreate(db)
    }

    fun addNote(title: String, content: String, date: String): Boolean {
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
            put("date", date)
        }

        val result = writableDatabase.insert("notes", null, values)
        return result != -1L
    }

    fun getAllNotes(): String {
        val cursor = readableDatabase.rawQuery(
            "SELECT * FROM notes ORDER BY id DESC", null
        )

        val result = StringBuilder()

        cursor.use {
            while (it.moveToNext()) {
                result.append("ID: ${it.getInt(0)}\n")
                result.append("Title: ${it.getString(1)}\n")
                result.append("Content: ${it.getString(2)}\n")
                result.append("Date: ${it.getString(3)}\n\n")
            }
        }

        return if (result.isEmpty()) {
            "No notes yet. Create your first note!"
        } else {
            result.toString()
        }
    }

    fun deleteNote(id: Int): Int {
        return writableDatabase.delete(
            "notes", "id = ?", arrayOf(id.toString())
        )
    }
}
