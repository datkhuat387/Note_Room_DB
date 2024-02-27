package com.example.note_room_db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.note_room_db.model.Note;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("SELECT * FROM notes")
    List<Note> getAllNote();
    @Insert
    void insert(Note note);
    @Update
    void update(Note note);
    @Delete
    void delete(Note note);
}
