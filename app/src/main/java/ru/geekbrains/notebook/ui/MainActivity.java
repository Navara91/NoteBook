package ru.geekbrains.notebook.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.impl.NotesRepoImpl;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NoteListFragment noteListFragment = new NoteListFragment();
    private NotesRepo notesRepo = new NotesRepoImpl(getParent());
    private NotesAdapter adapter = new NotesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, noteListFragment)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.notes_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_note_menu) {
            showEditFragment(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void showEditFragment(@Nullable NoteEntity item) {
        if (item == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new NoteEditFragment())
                    .addToBackStack(null)
                    .hide(noteListFragment)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(noteListFragment)
                    .add(R.id.fragment_container, NoteEditFragment.newInstance(item, notesRepo.getNotes()))
                    .addToBackStack(null)
                    .commit();
        }
    }
}