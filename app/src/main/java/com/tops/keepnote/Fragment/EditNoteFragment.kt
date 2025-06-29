package com.tops.keepnote.Fragment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.tops.keepnote.NotesData
import com.tops.keepnote.R
import com.tops.keepnote.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    private lateinit var binding: FragmentEditNoteBinding
    private lateinit var db: SQLiteDatabase
    private lateinit var oldNote: NotesData
    private  var position: Int = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNoteBinding.inflate(layoutInflater)


        db = requireActivity().openOrCreateDatabase("notepad", Context.MODE_PRIVATE, null)

        oldNote = arguments?.getSerializable("noteData") as NotesData
        position = arguments?.getInt("position") ?: -1

        binding.etTitle.setText(oldNote.title)
        binding.etDescription.setText(oldNote.description)

        binding.btnSaveNote.setOnClickListener {
            val newTitle = binding.etTitle.text.toString()
            val newDesc = binding.etDescription.text.toString()
            if (newTitle.isNotBlank() && newDesc.isNotBlank()) {
                // Update query
                db.execSQL(
                    "UPDATE notes SET title = ?, description = ? WHERE title = ? AND description = ?",
                    arrayOf(newTitle, newDesc, oldNote.title, oldNote.description)
                )

                Toast.makeText(requireContext(), "Note Updated", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_editNoteFragment_to_listNoteFragment)
            } else {
                Toast.makeText(requireContext(), "Fields can't be empty", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}