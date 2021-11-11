package ru.geekbrains.notebook.ui;

import android.os.Bundle;
import android.util.Log;
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

import static ru.geekbrains.notebook.utils.Constants.ALL_NOTES_SAVEINSTANCE;

public class NoteListFragment extends Fragment {

    private static final String TAG = "@@@";
    private RecyclerView recyclerView;
    private NotesRepo notesRepo = new NotesRepoImpl();
    private NotesAdapter adapter = new NotesAdapter();

    public void setNotesRepo(NotesRepo newNotesRepo) {
        notesRepo = newNotesRepo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated ListFragment called");

        if (savedInstanceState != null)
            loadInstance();

        if (notesRepo.getNotes().size() == 0)
            fillRepoByTestValues();

        initRecycler(view);
        saveInstance();
    }

    ArrayList<NoteEntity> getCurrentAllNotes(){
        return notesRepo.getNotes();
    }

    private void loadInstance() {
        Bundle argsAfter = getArguments();
        notesRepo.setAllNotes(argsAfter.getParcelableArrayList(ALL_NOTES_SAVEINSTANCE));
    }

    private void saveInstance(){
        Bundle argsBefore = new Bundle();
        argsBefore.putParcelableArrayList(ALL_NOTES_SAVEINSTANCE, notesRepo.getNotes());
        setArguments(argsBefore);
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
    }
}