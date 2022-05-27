package com.example.note.ui

import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.databinding.HomeFragmentBinding
import com.example.note.model.Note
import com.example.note.model.ui.CustomAdapter
import java.time.LocalDateTime
import android.util.Log
import com.example.note.BaseApplication
import com.example.note.data.NoteCacheMapper


class HomeFragment : Fragment(R.layout.home_fragment) {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var customAdapter: CustomAdapter
    private val mapper = NoteCacheMapper()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.AddNoteBtn.setOnClickListener{
            var newNote: Note = Note(null,
            "","", LocalDateTime.now())
            BaseApplication.database.getNoteDao().insert(mapper.mapFrom(newNote))
            var all = BaseApplication.database.getNoteDao().getAll()

            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNoteFragment(
               mapper.mapTo(all.last())))

        }
        val recyclerView: RecyclerView = binding.recyclerView

//        var notes: MutableList<Note> = mutableListOf(
//            Note( null,"title 1", "description 1", LocalDateTime.now()),
//            Note( null,"title 2", "description 2", LocalDateTime.of(2020,Month.JUNE,12,13,1)),
//            Note( null,"title 3", "description 3", LocalDateTime.of(2021,Month.JUNE,12,13,1)),
//            Note( null,"title 4", "description 4", LocalDateTime.of(2019,Month.JUNE,12,13,1)),
//            Note( null,"title 5", "description 5", LocalDateTime.of(2012,Month.JUNE,12,13,1)),
//            Note( null,"title 6", "description 6", LocalDateTime.of(2020,Month.JUNE,12,13,1)),
//            Note( null,"title 7", "description 7", LocalDateTime.of(2010,Month.JUNE,12,13,1)),
//            Note( null,"title 8", "description 8", LocalDateTime.of(2010,Month.JUNE,12,13,1)),
//            Note( null,"title 9", "description 9", LocalDateTime.of(2010,Month.JUNE,12,13,1)),
//            Note( null,"title 10", "description 10", LocalDateTime.of(2010,Month.JUNE,12,13,1)),
//        )

//        BaseApplication.database.getNoteDao().deleteAll()
//        notes.forEach{
//            BaseApplication.database.getNoteDao().insert(mapper.mapFrom(it))
//        }

        var notes: MutableList<Note> = mutableListOf()

        val localNotes = BaseApplication.database.getNoteDao().getAll()
        localNotes.forEach {
            notes.add(mapper.mapTo(it))
        }

        if (notes.size == 0){
            binding.ErrorNoNote.visibility = View.VISIBLE
        }
        else
            binding.ErrorNoNote.visibility =View.INVISIBLE

        customAdapter = CustomAdapter(notes)
        val layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter

        customAdapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
//                Log.d("Card Clicked", position.toString() + " " + notes[position].description)
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNoteFragment(notes[position]))
                customAdapter.notifyDataSetChanged()

            }
        })
        customAdapter.setOnDeleteBtnClickListener(object : CustomAdapter.onDeleteBtnClickListener{
            override fun onDeleteBtnClick(position: Int) {

                Log.d("Delete Button Clicked", position.toString())
                BaseApplication.database.getNoteDao().delete(mapper.mapFrom(notes[position]))
                notes.remove(notes[position])

                if (notes.size == 0){
                    binding.ErrorNoNote.visibility = View.VISIBLE
                }
                else
                    binding.ErrorNoNote.visibility =View.INVISIBLE

                customAdapter.notifyItemRemoved(position)
                }
        })
    }
}