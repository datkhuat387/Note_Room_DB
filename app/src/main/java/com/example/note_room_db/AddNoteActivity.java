package com.example.note_room_db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.note_room_db.db.NoteDB;
import com.example.note_room_db.model.Note;

public class AddNoteActivity extends AppCompatActivity {
    private EditText edt_title,edt_content;
    private ImageView img_back;
    private TextView tv_save;
    private NoteDB noteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edt_title = findViewById(R.id.edt_title);
        edt_content = findViewById(R.id.edt_content);
        img_back = findViewById(R.id.img_back);
        tv_save = findViewById(R.id.tv_save);

        noteDB = NoteDB.getInstance(this);
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void saveNote() {
        final String title = edt_title.getText().toString().trim();
        final String content = edt_content.getText().toString().trim();

        if (content.isEmpty()&&title.isEmpty()) {
            Toast.makeText(this, "Please enter note content", Toast.LENGTH_SHORT).show();
            return;
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Note note = new Note(title,content);
                noteDB.noteDAO().insert(note);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        edt_title.setText("");
//                        edt_content.setText("");
                        Toast.makeText(AddNoteActivity.this, "Note saved", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}