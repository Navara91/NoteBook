package ru.geekbrains.notebook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.impl.NotesRepoImpl;

public class NoteListFragment extends Fragment {

    private static final String TAG = "@@@";
    private RecyclerView recyclerView;
    private NotesRepo notesRepo = new NotesRepoImpl(getActivity());
    private NotesAdapter adapter = new NotesAdapter();
    private NoteEntity note = null;
    private ArrayList<NoteEntity> allNotes = null;
    private View viewByInitRecycler;

    public NoteListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewByInitRecycler = view;
        if (savedInstanceState == null) {
            fillRepoByTestValues();
            initRecycler(view);
        }


    }

    private void initRecycler(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((MainActivity) requireActivity());
        adapter.setData(notesRepo.getNotes());
    }

    private void fillRepoByTestValues() {
        notesRepo.createNote(new NoteEntity("заметка 1",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 2",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 3",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 4",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 5",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 6",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 7",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 8",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 9",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 10",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
        notesRepo.createNote(new NoteEntity("заметка 11",
                "какой-то длинный текст ывафывафыfdsgdfgsdfgagdfgsв"));
    }

    public void reInitRecycler() {
        initRecycler(viewByInitRecycler);
    }
}