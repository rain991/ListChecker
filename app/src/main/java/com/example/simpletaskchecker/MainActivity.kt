package com.example.simpletaskchecker

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewNotes: RecyclerView
    private val notes = ArrayList<Note>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewNotes = findViewById<RecyclerView>(R.id.recyclerViewNotes)
        if (notes.isEmpty()) {
            notes.add(Note("Барбер", "Зробити зачіску", "Понеділок", 2))
            notes.add(Note("Волейбол", "Гра в шкільній команді", "Вівторок", 1))
            notes.add(Note("Магазин", "Купити нову куртку", "Понеділок", 3))
            notes.add(Note("Стоматолог", "Лікування зуба", "Понеділок", 2))
            notes.add(Note("Магазин", "Купити нові духи", "Середа", 1))
            notes.add(Note("Плавання", "Тренування", "Вівторок", 3))
            notes.add(Note("Магазин", "Купити продукти", "Понеділок", 3))
        }
        val adapter = NotesAdapter(notes)
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        recyclerViewNotes.adapter = adapter
    }


    fun addClickButtonOnClick(view: View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivityForResult(intent, 220)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 220 && resultCode == RESULT_OK) {
            val resultBundle = data?.extras
            if (resultBundle != null) {
                val resultTitle = resultBundle.getString("Title").toString()
                val resultDescription = resultBundle.getString("Description").toString()
                val resultDayOfWeek = resultBundle.getString("DayOfWeek").toString()
                val resultPriority = resultBundle.getInt("Priority")
                val noteResult = Note(resultTitle, resultDescription, resultDayOfWeek, resultPriority)
                notes.add(noteResult)
            }
        }
    }


}