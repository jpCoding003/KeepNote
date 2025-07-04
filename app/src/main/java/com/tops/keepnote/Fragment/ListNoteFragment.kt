package com.tops.keepnote.Fragment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.keepnote.NotesData
import com.tops.keepnote.R
import com.tops.keepnote.adapter.MyAdapter
import com.tops.keepnote.databinding.FragmentListNoteBinding

class ListNoteFragment : Fragment(), MyAdapter.NoteClickListener{


    private lateinit var db: SQLiteDatabase
    private lateinit var binding: FragmentListNoteBinding
    private lateinit var adapter: MyAdapter
    private val notes = mutableListOf<NotesData>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = requireActivity().openOrCreateDatabase("notepad", Context.MODE_PRIVATE, null)
        val cursor1 = db.rawQuery("SELECT * FROM notes", null)

        while (cursor1.moveToNext()) {
            val title = cursor1.getString(0)
            val description = cursor1.getString(1)
            if (title.isNotBlank() && description.isNotBlank()) {
                notes.add(NotesData(title, description))
            }
            Log.i("Note", "Title: $title, Description: $description")
        }
        cursor1.close()

        binding.rvNoteListView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyAdapter(notes, this)
        binding.rvNoteListView.adapter = adapter


        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_listNoteFragment_to_addNoteFragment)
        }
    }

    override fun onNoteDelete(notesData: NotesData, position: Int) {

        db.execSQL("DELETE FROM notes WHERE title = '${notesData.title}' AND description = '${notesData.description}'")
        notes.removeAt(position)
        adapter.notifyItemRemoved(position)
    }


    override fun onNoteClick(notesData: NotesData, position: Int) {
        val bundle = Bundle().apply {
            putSerializable("noteData", notesData)
            putInt("position", position)
        }
        findNavController().navigate(R.id.action_listNoteFragment_to_editNoteFragment, bundle)
    }
}