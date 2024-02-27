package com.example.note_room_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.note_room_db.adapter.NoteAdapter;
import com.example.note_room_db.dao.NoteDAO;
import com.example.note_room_db.db.NoteDB;
import com.example.note_room_db.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv_list_note;
    private ImageView img_add;
    private NoteAdapter noteAdapter;
    private NoteDAO noteDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv_list_note = findViewById(R.id.rcv_list_note);
        img_add = findViewById(R.id.img_add);

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        rcv_list_note.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(new ArrayList<>());
        rcv_list_note.setAdapter(noteAdapter);
        NoteDB noteDB = NoteDB.getInstance(this);
        noteDAO = noteDB.noteDAO();
        loadNotes();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<Note> notes = noteDAO.getAllNote();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noteAdapter.setNotes(notes);
                    }
                });
            }
        });
    }
}