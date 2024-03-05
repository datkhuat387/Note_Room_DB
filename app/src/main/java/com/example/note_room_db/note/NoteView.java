package com.example.note_room_db.note;

import com.example.note_room_db.model.Note;

import java.util.List;

public interface NoteView {
    void showNotes(List<Note> notes);
    void showNoteAdded();
    void startAddNoteActivity();
    void showToastCheck();
}
