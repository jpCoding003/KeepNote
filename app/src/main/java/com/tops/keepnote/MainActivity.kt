package com.tops.keepnote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.keepnote.Fragment.NewNoteFragment
import com.tops.keepnote.adapter.MyAdapter
import com.tops.keepnote.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.mainActivitytoolbar)
        title = "NOTES"

        adapter = MyAdapter(NoteData.notes)
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = adapter

//        val noteAdd = intent.getStringExtra("newNote")
//        Log.i(TAG, "Data == $noteAdd")
//        noteAdd?.let {
//            noteList.add(it)
//            adapter.notifyItemInserted(noteList.size-1)
//        }

        if (intent.hasExtra("newNote")) {
            val newNote = intent.getStringExtra("newNote")
            if (!newNote.isNullOrEmpty()) {
                NoteData.notes.add(newNote)
                adapter.notifyItemInserted(NoteData.notes.size - 1)
            }
            intent.removeExtra("newNote") // prevent re-adding on config change
        }


        addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(
                menu: Menu,
                menuInflater: MenuInflater
            ) {
                menuInflater.inflate(R.menu.actionbar_menu_items,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.addNote){
                    val intent= Intent(applicationContext, DashBoard::class.java)
                    startActivity(intent)
                    return true
                }
                return false
            }

        })


    }

}