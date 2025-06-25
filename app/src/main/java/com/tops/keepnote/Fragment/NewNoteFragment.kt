package com.tops.keepnote.Fragment

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.LogWriter
import com.tops.keepnote.MainActivity
import com.tops.keepnote.R
import com.tops.keepnote.databinding.FragmentNewNoteBinding
import kotlin.math.log

private const val TAG = "NewNoteFragment"
class NewNoteFragment : Fragment() {

    private lateinit var binding: FragmentNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btncreate.setOnClickListener {
            if (binding.newNoteName.text.toString().isNotEmpty()){
                val intent= Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("newNote",binding.newNoteName.text.toString())
                startActivity(intent)
                activity?.finish()
            }

        }
        activity?.title = "New Note"

    }

}