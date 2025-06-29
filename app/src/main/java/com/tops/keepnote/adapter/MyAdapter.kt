package com.tops.keepnote.adapter

import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.keepnote.NotesData
import com.tops.keepnote.databinding.ItemRowBinding

private const val TAG = "MyAdapter"
class MyAdapter(private val notesData: List<NotesData>): RecyclerView.Adapter<MyAdapter.NotesViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        val binding= ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NotesViewHolder,
        position: Int
    ) {
        val note = notesData[position]
        holder.binding.noteTitle.setText(note.title)
        holder.binding.noteDescription.setText(note.description)
        Log.i(TAG, "Description=========> ${note.description}")
    }

    override fun getItemCount(): Int {
        return notesData.size
    }

    class NotesViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)
}