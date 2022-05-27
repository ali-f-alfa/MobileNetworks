package com.example.note.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.LayoutInflaterCompat
import androidx.core.view.ViewPropertyAnimatorListenerAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.note.databinding.HomeFragmentBinding
import com.example.note.model.Note
import com.example.note.model.ui.CustomAdapter
import com.example.note.model.ui.ItemViewNote
import java.time.LocalDateTime
import java.time.Month
import java.util.*
import java.util.zip.Inflater

class HomeFragment : Fragment(R.layout.home_fragment) {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var customAdapter: CustomAdapter

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
//        binding.StartBtn.setOnClickListener{
//            findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
//        }

        val recyclerView: RecyclerView = binding.recyclerView
        val notes: List<Note> = listOf(
            Note( "title 1", "description 1", LocalDateTime.now()),
            Note( "title 2", "description 2", LocalDateTime.of(2020,Month.JUNE,12,13,1)),
            Note( "title 3", "description 3", LocalDateTime.of(2021,Month.JUNE,12,13,1)),
            Note( "title 4", "description 4", LocalDateTime.of(2019,Month.JUNE,12,13,1)),
            Note( "title 5", "description 5", LocalDateTime.of(2022,Month.JUNE,12,13,1)),
            Note( "title 6", "description 6", LocalDateTime.of(2030,Month.JUNE,12,13,1)),
        )

        customAdapter = CustomAdapter(notes)
        val layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter

    }
}