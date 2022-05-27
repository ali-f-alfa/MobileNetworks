package com.example.note.model.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note.model.Note
import android.view.View
import android.widget.TextView
import android.widget.ImageButton
import com.example.note.R
import androidx.annotation.NonNull


internal class CustomAdapter(private var notes: MutableList<Note>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    public var adapterNote: MutableList<Note> = notes
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    internal inner class MyViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view) {
        var itemTitle: TextView = view.findViewById(R.id.Title_view)
        var itemDateOfModifed: TextView = view.findViewById(R.id.DataOfModified_view)
        var deletItemBtn: ImageButton = view.findViewById(R.id.DeleteBtn_View)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
            deletItemBtn.setOnClickListener {
                val position = adapterPosition
                Log.d("Delete Button Clicked", position.toString())
                Log.d("size of notes:", adapterNote.size.toString())
                adapterNote.remove(adapterNote[position])
                notifyItemRemoved(position)
            }
        }
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_note, parent, false)
        return MyViewHolder(itemView, mListener)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = adapterNote[position]
        holder.itemTitle.text = item.title
        holder.itemDateOfModifed.text = item.lastModifiedDate.toString()
    }
    override fun getItemCount(): Int {
        return adapterNote.size
    }
}