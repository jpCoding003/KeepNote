package com.tops.keepnote.Fragment

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tops.keepnote.R
import com.tops.keepnote.databinding.FragmentListNoteBinding

class ListNoteFragment : Fragment() {

    private lateinit var binding: FragmentListNoteBinding

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

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_listNoteFragment_to_addNoteFragment)

        }
    }

}