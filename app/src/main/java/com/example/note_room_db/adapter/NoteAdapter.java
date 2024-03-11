package com.example.note_room_db.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_room_db.R;
import com.example.note_room_db.model.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> noteList;
    private OnNoteDeleteListener onNoteDeleteListener;

    public void setOnNoteDeleteListener(OnNoteDeleteListener listener) {
        this.onNoteDeleteListener = listener;
    }
    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }
    public void setNotes(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Note note = noteList.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_content.setText(note.getContent());
        holder.tv_delete.setOnClickListener(view -> {
            if (onNoteDeleteListener != null) {
                onNoteDeleteListener.onNoteDelete(note);
            }
        });
        holder.itemView.setOnClickListener(view -> {

        });
    }
    public interface OnNoteDeleteListener {
        void onNoteDelete(Note note);
    }
    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title,tv_content,tv_delete;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}
