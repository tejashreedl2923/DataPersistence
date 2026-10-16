package com.example.datapersistencedemo

import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    private lateinit var notesContainer: LinearLayout
    private lateinit var tvEmptyNotes: TextView
    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)

        etTitle = findViewById(R.id.etNoteTitle)
        etContent = findViewById(R.id.etNoteContent)
        etSearch = findViewById(R.id.etSearch)
        notesContainer = findViewById(R.id.notesContainer)
        tvEmptyNotes = findViewById(R.id.tvEmptyNotes)

        findViewById<Button>(R.id.btnSaveNote).setOnClickListener {
            saveNote()
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?, start: Int, before: Int, count: Int
            ) {
                displayNotes(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        displayNotes()
    }

    private fun saveNote() {
        val title = etTitle.text.toString().trim()
        val content = etContent.text.toString().trim()

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(
                this, "Please enter a title and content",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val date = SimpleDateFormat(
            "dd MMM yyyy", Locale.getDefault()
        ).format(Date())

        if (db.addNote(title, content, date)) {
            etTitle.text.clear()
            etContent.text.clear()
            etSearch.text.clear()
            displayNotes()

            Toast.makeText(
                this, "Your thought has been saved ♡",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this, "Unable to save note", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun displayNotes(query: String = "") {
        notesContainer.removeAllViews()

        val cursor = db.readableDatabase.rawQuery(
            "SELECT * FROM notes WHERE title LIKE ? " +
                    "OR content LIKE ? ORDER BY id DESC",
            arrayOf("%$query%", "%$query%")
        )

        var count = 0
        var row: LinearLayout? = null

        cursor.use {
            while (it.moveToNext()) {
                if (count % 2 == 0) {
                    row = LinearLayout(this).apply {
                        orientation = LinearLayout.HORIZONTAL
                        weightSum = 2f
                    }

                    val rowParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    rowParams.bottomMargin = dp(12)
                    notesContainer.addView(row, rowParams)
                }

                val id = it.getInt(0)
                val title = it.getString(1)
                val content = it.getString(2)
                val date = it.getString(3)

                val card = LinearLayout(this).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(dp(14), dp(14), dp(14), dp(12))
                    background = roundedBackground("#F8E8EB", 20)
                    elevation = dp(3).toFloat()
                }

                val cardParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
                cardParams.marginEnd = dp(6)
                card.layoutParams = cardParams

                val heading = TextView(this).apply {
                    text = "♡"
                    textSize = 22f
                    setTextColor(Color.parseColor("#A64D68"))
                }

                val titleView = TextView(this).apply {
                    text = title
                    textSize = 17f
                    typeface = Typeface.create(
                        "serif", Typeface.BOLD
                    )
                    setTextColor(Color.parseColor("#57283A"))
                    maxLines = 2
                }

                val contentView = TextView(this).apply {
                    text = content
                    textSize = 14f
                    setTextColor(Color.parseColor("#765361"))
                    maxLines = 5
                    setPadding(0, dp(8), 0, dp(10))
                }

                val dateView = TextView(this).apply {
                    text = date
                    textSize = 11f
                    setTextColor(Color.parseColor("#A16C7D"))
                }

                val deleteButton = Button(this).apply {
                    text = "Delete"
                    textSize = 11f
                    isAllCaps = false
                    setTextColor(Color.parseColor("#6B2437"))
                    backgroundTintList =
                        android.content.res.ColorStateList.valueOf(
                            Color.parseColor("#F4DCE4")
                        )

                    setOnClickListener {
                        db.deleteNote(id)
                        displayNotes(etSearch.text.toString())
                        Toast.makeText(
                            this@MainActivity,
                            "Note deleted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                card.setOnClickListener {
                    etTitle.setText(title)
                    etContent.setText(content)
                    Toast.makeText(
                        this,
                        "You can copy or edit this note in the fields above",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                card.addView(heading)
                card.addView(titleView)
                card.addView(contentView)
                card.addView(dateView)
                card.addView(deleteButton)

                row?.addView(card)
                count++
            }
        }

        tvEmptyNotes.visibility =
            if (count == 0) View.VISIBLE else View.GONE

        tvEmptyNotes.text = if (query.isNotEmpty()) {
            "No matching thoughts found."
        } else {
            "Your story starts here ♡"
        }
    }

    private fun roundedBackground(
        color: String,
        radius: Int
    ): GradientDrawable {
        return GradientDrawable().apply {
            setColor(Color.parseColor(color))
            cornerRadius = dp(radius).toFloat()
        }
    }

    private fun dp(value: Int): Int {
        return (value * resources.displayMetrics.density).toInt()
    }
}
