package com.example.simpletaskchecker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import kotlin.properties.Delegates

class AddNoteActivity : AppCompatActivity() {
    private lateinit var editTextTitle : EditText
    private lateinit var editTextDescription : EditText
    private lateinit var spinnerDaysOfWeek: Spinner
    private lateinit var radioGroupPriority : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDescription = findViewById(R.id.editTextDescription)
        spinnerDaysOfWeek = findViewById(R.id.spinner)
        radioGroupPriority = findViewById(R.id.radioGroup)
    }

    fun onClickSaveNote(view: View) {
        var priority : Int? = null
        val currentTitle = editTextTitle.text.toString().trim()
        val currentDescription = editTextDescription.text.toString().trim()
        val currentSpinner = spinnerDaysOfWeek.selectedItem.toString()  // Day of week
        val radioButtonId = radioGroupPriority.checkedRadioButtonId
        val currentRadioButton = findViewById<RadioButton>(radioButtonId)
        when(currentRadioButton.text.toString()){
            R.string.highest_priority.toString()->{
                priority = 1
            }
            R.string.medium_priority.toString()->{
                priority = 2
            }
            R.string.low_priority.toString()->{
                priority = 3
            }
        }
        if(priority != null){
            val note = Note(currentTitle, currentDescription, currentSpinner, priority)
        }
        val currentBundle = Bundle()
        currentBundle.putString("Title", currentTitle)
        currentBundle.putString("Description", currentDescription)
        currentBundle.putString("DayOfWeek", currentSpinner)
        if (priority != null) {
            currentBundle.putInt("Priority", priority)
        }

        val resultIntent = Intent(this, MainActivity::class.java)
        resultIntent.putExtras(currentBundle)
        setResult(RESULT_OK, resultIntent)
        finish()
    }


}