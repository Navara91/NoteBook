package ru.geekbrains.notebook.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;

public class NotesAdapter extends RecyclerView.Adapter<NoteVh> {

    private ArrayList<NoteEntity> data = new ArrayList<>();
    private MainActivity mainActivity;

    public void setData(ArrayList<NoteEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent,
                false);
        return new NoteVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVh holder, int position) {
        NoteEntity note = getItem(position);
        holder.noteItemView.setOnClickListener(v ->
                mainActivity.showEditFragment(note, data)
        );
        holder.titleTextView.setText(note.getTitle());
        holder.detailTextView.setText(note.getDetails());
    }

    private NoteEntity getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void setOnItemClickListener(MainActivity listener){
        mainActivity = listener;
    }
}
