package ru.geekbrains.notebook.ui;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;

public class NotesAdapter extends RecyclerView.Adapter<NoteVh> {

    private List<NoteEntity> data = new ArrayList<>();
    private OnItemClickListener clickListener = null;
    private NoteListFragment.InterfaceOpenEditFragment interfaceOpenEditFragment = null;

    public void setData(List<NoteEntity> data) {
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
                interfaceOpenEditFragment.showEditFragment(note)
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

//    void setOnItemClickListener(OnItemClickListener listener) {
//        clickListener = listener;
//    }

    void setOnItemClickListener(NoteListFragment.InterfaceOpenEditFragment listener) {
        interfaceOpenEditFragment = listener;
    }

    interface OnItemClickListener {
        void onItemClick(NoteEntity item);
    }

}
