package com.tops.keepnote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tops.keepnote.databinding.FragmentNewNoteBinding
import com.tops.keepnote.databinding.NoteRowItemBinding

class MyAdapter(private val notes: ArrayList<String>): RecyclerView.Adapter<MyAdapter.NoteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): NoteViewHolder {
        val binding = NoteRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {
        var noteTitle = notes[position]
        holder.binding.noteName.setText(noteTitle)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NoteViewHolder(val binding: NoteRowItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.noteName.setOnClickListener {
                Toast.makeText(binding.root.context , "${binding.noteName.text.toString()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}