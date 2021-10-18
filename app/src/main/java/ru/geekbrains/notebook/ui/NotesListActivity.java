package ru.geekbrains.notebook.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.impl.NotesRepoImpl;

import static ru.geekbrains.notebook.utils.Constants.ALL_NOTES_CODE;
import static ru.geekbrains.notebook.utils.Constants.DETAIL_SAVEINSTANCE;
import static ru.geekbrains.notebook.utils.Constants.ID_SAVEINSTANCE;
import static ru.geekbrains.notebook.utils.Constants.KEY_SAVEINSTANCE;
import static ru.geekbrains.notebook.utils.Constants.TITLE_SAVEINSTANCE;

public class NotesListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private NotesRepo notesRepo = new NotesRepoImpl(this);
    private NotesAdapter adapter = new NotesAdapter();
    private boolean isFirstLaunch = true;
    private ActivityResultLauncher<Intent> noteEditActivityLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        if (savedInstanceState == null) {
            fillRepoByTestValues();
            fillRepoValues();

            initToolbar();
            initRecycler();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isFirstLaunch == false) {
            initRecycler();
        } else {
            isFirstLaunch = false;
        }
    }

    private void fillRepoValues() {
        noteEditActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();
                notesRepo.setAllNotes(intent.getParcelableArrayListExtra(ALL_NOTES_CODE));
                notesRepo.setNoteContent(intent.getParcelableExtra(KEY_SAVEINSTANCE));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_note_menu) {
            openNoteScreen(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openNoteScreen(@Nullable NoteEntity item) {
        if (noteEditActivityLauncher != null) {
            Intent intent = NoteEditActivity.getLaunchIntent(this, item, notesRepo.getNotes());
            noteEditActivityLauncher.launch(intent);
        }
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::onItemClick);
        adapter.setData(notesRepo.getNotes());
    }

    private void onItemClick(NoteEntity item) {
        openNoteScreen(item);
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
        isFirstLaunch = false;
    }

}