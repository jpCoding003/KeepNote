package com.tops.keepnote.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.keepnote.NotesData
import com.tops.keepnote.databinding.ItemRowBinding

private const val TAG = "MyAdapter"
class MyAdapter(private val notesData: MutableList<NotesData>,private  val listener: NoteClickListener): RecyclerView.Adapter<MyAdapter.NotesViewHolder>() {



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

        holder.binding.btnDelete.setOnClickListener {
            listener.onNoteDelete(notesData[position],position)
        }
        holder.binding.btnEdit.setOnClickListener {
            listener.onNoteClick(notesData[position],position)
        }
    }

    override fun getItemCount(): Int {
        return notesData.size
    }



    inner class NotesViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    interface NoteClickListener {
        fun onNoteClick(notesData: NotesData, position: Int)
        fun onNoteDelete(notesData: NotesData, position: Int)
    }
    fun removeNote(position: Int) {
        notesData.removeAt(position)
        notifyItemRemoved(position)
    }
}