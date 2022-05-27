package com.example.note.model.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.ItemViewNoteBinding
import com.example.note.model.Note
import android.view.View
import android.widget.TextView
import com.example.note.R
import androidx.annotation.NonNull


internal class CustomAdapter(private var notes: List<Note>) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTitle: TextView = view.findViewById(R.id.Title_view)
        var itemDateOfModifed: TextView = view.findViewById(R.id.DataOfModified_view)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_note, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = notes[position]
        holder.itemTitle.text = item.title
        holder.itemDateOfModifed.text = item.lastModifiedDate.toString()
    }
    override fun getItemCount(): Int {
        return notes.size
    }
}