package ru.geekbrains.notebook.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.impl.NotesRepoImpl;

import static ru.geekbrains.notebook.utils.Constants.ALL_NOTES_CODE;
import static ru.geekbrains.notebook.utils.Constants.KEY_SAVEINSTANCE;

public class NoteListFragment extends Fragment implements NotesAdapter.OnItemClickListener {


    private RecyclerView recyclerView;
    private NotesRepo notesRepo = new NotesRepoImpl(getActivity());
    private NotesAdapter adapter = new NotesAdapter();
    private boolean isFirstLaunch = true;
    private InterfaceOpenEditFragment interfaceOpenEditFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

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

//        if (savedInstanceState == null) {
            fillRepoByTestValues();
//            fillRepoValues();


            initRecycler(view);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();

//        if (isFirstLaunch == false) {
//            initRecycler();
//        } else {
//            isFirstLaunch = false;
//        }
    }

    interface InterfaceOpenEditFragment{
        void showEditFragment(@Nullable NoteEntity item);

    }

    private void initRecycler(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(InterfaceOpenEditFragment::showEditFragment);
        adapter.setData(notesRepo.getNotes());
    }

//    public void onItemClick(NoteEntity item) {
//        interfaceOpenEditFragment.showEditFragment(item);
//    }

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
        isFirstLaunch = false;
    }


}