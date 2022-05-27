package com.example.note.ui

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.note.BaseApplication
import com.example.note.R
import com.example.note.data.NoteCacheMapper
import com.example.note.databinding.HomeFragmentBinding
import com.example.note.databinding.NoteFragmentBinding
import com.example.note.model.Note
import java.time.LocalDateTime

class NoteFragment: Fragment(R.layout.note_fragment) {
    private var _binding: NoteFragmentBinding? = null
    private val binding get() = _binding!!

    private val args :NoteFragmentArgs by navArgs()
    private val mapper = NoteCacheMapper()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NoteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.TitleText.text = Editable.Factory.getInstance().newEditable(args.Note.title.toString())
        binding.DescriptionText.text = Editable.Factory.getInstance().newEditable(args.Note.description.toString())

        binding.SaveBtn.setOnClickListener{

            args.Note.title = binding.TitleText.text.toString()
            args.Note.description = binding.DescriptionText.text.toString()
            args.Note.lastModifiedDate = LocalDateTime.now()

            BaseApplication.database.getNoteDao().edit(mapper.mapFrom(args.Note))
            findNavController().popBackStack()

        }
    }
}