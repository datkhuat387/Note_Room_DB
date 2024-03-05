package com.example.note_room_db.repository;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.note_room_db.note.NoteView;
import com.example.note_room_db.dao.NoteDAO;
import com.example.note_room_db.model.Note;

import java.util.List;

public class NoteRepository {
    private NoteView noteView;
    private NoteDAO noteDAO;

    public NoteRepository(NoteView noteView, NoteDAO noteDAO) {
        this.noteView = noteView;
        this.noteDAO = noteDAO;
    }
    public void loadNotes() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final List<Note> notes = noteDAO.getAllNote();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                @Override
                public void run() {
                    noteView.showNotes(notes);
                    }
                });
            }
        });
    }
    public void addNote(String title, String content) {
        if (title.isEmpty() || content.isEmpty()) {
            noteView.showToastCheck();
            return;
        }
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Note note = new Note(title, content);
                noteDAO.insert(note);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        noteView.showNoteAdded();
                    }
                });
            }
        });
    }

    public void onAddNoteClicked() {
        noteView.startAddNoteActivity();
    }
}