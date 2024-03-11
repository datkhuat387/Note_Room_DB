package com.example.note_room_db.repository;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.example.note_room_db.note.NoteView;
import com.example.note_room_db.dao.NoteDAO;
import com.example.note_room_db.model.Note;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteRepository {
    private NoteView noteView;
    private NoteDAO noteDAO;
    private Executor executor;

    public NoteRepository(NoteView noteView, NoteDAO noteDAO) {
        this.noteView = noteView;
        this.noteDAO = noteDAO;
        this.executor = Executors.newSingleThreadExecutor();
    }
    public void loadNotes() {
        executor.execute(() -> {
            List<Note> notes = noteDAO.getAllNote();
            postNotesToMainThread(notes);
        });
    }
    private void postNotesToMainThread(List<Note> notes) {
        new Handler(Looper.getMainLooper()).post(() -> {
            noteView.showNotes(notes);
        });
    }
    public void addNote(String title, String content) {
        if (title.isEmpty() || content.isEmpty()) {
            noteView.showToastCheck();
            return;
        }

        executor.execute(() -> {
            Note note = new Note(title, content);
            noteDAO.insert(note);
            noteView.showNoteAdded();
        });
    }
    public void deleteNote(Note note) {
        executor.execute(() -> {
            noteDAO.delete(note);
            loadNotes();
        });
    }
    public void onAddNoteClicked() {
        noteView.startAddNoteActivity();
    }
}