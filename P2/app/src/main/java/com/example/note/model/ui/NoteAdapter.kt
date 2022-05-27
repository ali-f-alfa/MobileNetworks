package com.example.note.model.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.data.NoteCacheMapper
import com.example.note.model.Note
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalQueries.localDate


internal class CustomAdapter(private var GlobalNotes: MutableList<Note>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener
    private lateinit var m2Listener: onDeleteBtnClickListener

    private val mapper = NoteCacheMapper()

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    interface onDeleteBtnClickListener{
        fun onDeleteBtnClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    fun setOnDeleteBtnClickListener(listener: onDeleteBtnClickListener){
        m2Listener = listener
    }

    @RequiresApi(Build.VERSION_CODES.O)
    internal inner class MyViewHolder(view: View, listener: onItemClickListener, deleteListener: onDeleteBtnClickListener) : RecyclerView.ViewHolder(view) {
        var itemTitle: TextView = view.findViewById(R.id.Title_view)
        var itemDateOfModifed: TextView = view.findViewById(R.id.DataOfModified_view)
        var deletItemBtn: ImageButton = view.findViewById(R.id.DeleteBtn_View)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
            deletItemBtn.setOnClickListener {
                deleteListener.onDeleteBtnClick(adapterPosition)
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_note, parent, false)
        return MyViewHolder(itemView, mListener, m2Listener)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = GlobalNotes[position]
        holder.itemTitle.text = item.title

        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy  hh:mm")
        val formattedString: String? = item.lastModifiedDate?.format(formatter)
        holder.itemDateOfModifed.text = "Edited at: $formattedString"
    }
    override fun getItemCount(): Int {
        return GlobalNotes.size
    }
}