package com.example.note_room_db.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.note_room_db.R;
import com.example.note_room_db.repository.NoteRepository;
import com.example.note_room_db.db.NoteDB;
import com.example.note_room_db.model.Note;

import java.util.List;

public class AddNoteActivity extends AppCompatActivity implements NoteView{
    private EditText edt_title,edt_content;
    private ImageView img_back;
    private TextView tv_save;
    private NoteDB noteDB;
    private NoteRepository noteRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edt_title = findViewById(R.id.edt_title);
        edt_content = findViewById(R.id.edt_content);
        img_back = findViewById(R.id.img_back);
        tv_save = findViewById(R.id.tv_save);

        noteDB = NoteDB.getInstance(this);
        noteRepository = new NoteRepository(this, noteDB.noteDAO());
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String title = edt_title.getText().toString().trim();
                final String content = edt_content.getText().toString().trim();

                noteRepository.addNote(title,content);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void showNotes(List<Note> notes) {

    }

    @Override
    public void showNoteAdded() {
        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        edt_title.setText("");
        edt_content.setText("");
    }
    @Override
    public void showToastCheck() {
        Toast.makeText(this, "Please enter title and content", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startAddNoteActivity() {

    }

}