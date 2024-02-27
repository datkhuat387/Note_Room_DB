package com.example.note_room_db.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.note_room_db.dao.NoteDAO;
import com.example.note_room_db.model.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDB extends RoomDatabase {
    private static NoteDB instance;

    public abstract NoteDAO noteDAO();

    public static synchronized NoteDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDB.class,"note_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
