package ru.geekbrains.notebook.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.impl.NotesRepoImpl;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "@@@";
    private Toolbar toolbar;
    private NoteListFragment noteListFragment = new NoteListFragment();

    public void setNoteRepo(NotesRepo newNotesRepo) {
        noteListFragment.setNotesRepo(newNotesRepo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        if (savedInstanceState == null)
            firstLaunchMainFragment();
    }

    private void firstLaunchMainFragment() {
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
            showEditFragment(null, noteListFragment.getCurrentAllNotes());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void showEditFragment(@Nullable NoteEntity item, @Nullable ArrayList<NoteEntity> allNotes) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, NoteEditFragment.newInstance(item, allNotes))
                    .addToBackStack(null)
                    .commit();
    }
}