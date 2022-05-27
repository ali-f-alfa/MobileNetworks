package com.example.note.model.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.note.R
import com.example.note.databinding.ItemViewNoteBinding
import com.example.note.model.Note

class ItemViewNote @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var _binding: ItemViewNoteBinding? = null
    val binding get() = _binding!!

    var note: Note? = null
    set(value){
        field = value
        note?.let { note ->
            binding.TitleView.text = note.title
            binding.DataOfModifiedView.text = note.lastModifiedDate.toString()
            binding.DeleteBtnView.setImageResource(R.drawable.ic_baseline_delete_24)
        }
    }

    var listener: Listener? = null

    init {
        _binding = ItemViewNoteBinding.inflate(LayoutInflater.from(context), this, true)
        binding.root.setOnClickListener{
            note?.let { note ->
            listener?.onItemClicked(note)
            }
        }
    }


    interface Listener{
        fun onItemClicked(note: Note)
    }
}