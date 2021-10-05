package ru.geekbrains.notebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.impl.NotesRepoImpl;

public class NotesListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private NotesRepo notesRepo = new NotesRepoImpl();
    private NotesAdapter adapter = new NotesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        fillRepoByTestValues();

        initToolbar();
        initRecycler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_note_menu) {
            openNoteScreen();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openNoteScreen() {
        Intent intent = new Intent(this, NoteEditActivity.class);
        startActivity(intent);
    }

    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRecycler(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setData(notesRepo.getNotes());
    }

    private void fillRepoByTestValues() {
        notesRepo.createNote(new NoteEntity("заметка 1", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 2", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 3", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 4", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 5", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 6", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 7", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 8", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 9", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 10", "какой-то длинный текст ывафывафыв"));
        notesRepo.createNote(new NoteEntity("заметка 11", "какой-то длинный текст ывафывафыв"));
    }
}