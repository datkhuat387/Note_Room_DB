package com.example.note_room_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.note_room_db.adapter.NoteAdapter;
import com.example.note_room_db.repository.NoteRepository;
import com.example.note_room_db.dao.NoteDAO;
import com.example.note_room_db.db.NoteDB;
import com.example.note_room_db.model.Note;
import com.example.note_room_db.note.AddNoteActivity;
import com.example.note_room_db.note.NoteView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteView {
    private RecyclerView rcv_list_note;
    private ImageView img_add;
    private NoteAdapter noteAdapter;
    private NoteDAO noteDAO;
    private NoteRepository noteRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv_list_note = findViewById(R.id.rcv_list_note);
        img_add = findViewById(R.id.img_add);

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteRepository.onAddNoteClicked();
            }
        });

        rcv_list_note.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(new ArrayList<>());
        rcv_list_note.setAdapter(noteAdapter);

        NoteDAO noteDAO = NoteDB.getInstance(this).noteDAO();
        noteRepository = new NoteRepository(this, noteDAO);
        noteRepository.loadNotes();
    }
    public void showNotes(List<Note> notes) {
        noteAdapter.setNotes(notes);
    }

    @Override
    public void showNoteAdded() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        noteRepository.loadNotes();
    }
    @Override
    public void startAddNoteActivity() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void showToastCheck() {

    }
}