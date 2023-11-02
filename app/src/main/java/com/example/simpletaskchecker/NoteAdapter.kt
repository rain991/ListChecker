package com.example.simpletaskchecker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletaskchecker.NotesAdapter.NotesViewHolder
//private lateinit var onNoteClickListener: OnNoteClickListener
interface OnNoteClickListener{
        fun onNoteClick(position : Int){

        }
}

class NotesAdapter(private val notes: ArrayList<Note>) :
    RecyclerView.Adapter<NotesViewHolder?>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): NotesViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.note_item, viewGroup, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }


    override fun onBindViewHolder(notesViewHolder: NotesViewHolder, i: Int) {
        val note = notes[i]
        notesViewHolder.textViewTitle.text = note.title
        notesViewHolder.textViewDescription.text = note.description
        notesViewHolder.textViewDayOfWeek.text = note.dayOfWeek
        // lateinit var colorID : Int

        when (note.priority) {
            1 -> {
                val colorID = notesViewHolder.itemView.resources.getColor(android.R.color.holo_red_light)
                notesViewHolder.textViewTitle.setBackgroundColor(colorID)
            }

            2 -> {
                val colorID = notesViewHolder.itemView.resources.getColor(android.R.color.holo_orange_light)
                notesViewHolder.textViewTitle.setBackgroundColor(colorID)
            }

            3 -> {
                val colorID = notesViewHolder.itemView.resources.getColor(android.R.color.holo_green_light)
                notesViewHolder.textViewTitle.setBackgroundColor(colorID)
            }

        }
    }

    inner class NotesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView
        val textViewDescription: TextView
        val textViewDayOfWeek: TextView


        init {
            textViewTitle = itemView.findViewById(R.id.textViewTitle)
            textViewDescription = itemView.findViewById(R.id.textViewDescription)
            textViewDayOfWeek = itemView.findViewById(R.id.textViewDayOfWeek)
        }

    }
}