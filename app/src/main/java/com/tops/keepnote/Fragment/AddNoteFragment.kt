package com.tops.keepnote.Fragment

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tops.keepnote.R
import com.tops.keepnote.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {

    private lateinit var db: SQLiteDatabase
    private lateinit var binding: FragmentAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        db = requireActivity().openOrCreateDatabase("notepad", Context.MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS notes(title VARCHAR, description VARCHAR);")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDone.setOnClickListener {
            if (binding.etNotes.text!!.isEmpty()){
                binding.etNotes.setError("Text Must Not be Empty")
            }else{
//                db.execSQL("INSERT INTO notes(title, description) VALUES('"+binding.etNotes.text+"','"+binding.etDescription.text+"');")
                val contentValues = ContentValues()
                contentValues.put("title", binding.etNotes.text.toString())
                contentValues.put("description", binding.etDescription.text.toString())
                db.insert("notes", null, contentValues)
                findNavController().navigate(R.id.action_addNoteFragment_to_listNoteFragment)
            }
        }

    }
}