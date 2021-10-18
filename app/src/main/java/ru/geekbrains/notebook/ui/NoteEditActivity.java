package ru.geekbrains.notebook.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.impl.NotesRepoImpl;

import static ru.geekbrains.notebook.utils.Constants.ALL_NOTES_CODE;
import static ru.geekbrains.notebook.utils.Constants.DETAIL_SAVEINSTANCE;
import static ru.geekbrains.notebook.utils.Constants.EXTRA_KEY;
import static ru.geekbrains.notebook.utils.Constants.ID_SAVEINSTANCE;
import static ru.geekbrains.notebook.utils.Constants.KEY_SAVEINSTANCE;
import static ru.geekbrains.notebook.utils.Constants.TITLE_SAVEINSTANCE;

public class NoteEditActivity extends AppCompatActivity {

    private EditText titleEditText = null;
    private EditText detailEditText = null;
    private Button saveButton = null;
    private NoteEntity note = null;
    private NotesRepo notesRepo = new NotesRepoImpl(this);
    private ArrayList<NoteEntity> allNotes = null;

    static Intent getLaunchIntent(Context context, NoteEntity note, ArrayList<NoteEntity> allNotes){
        Intent intent = new Intent(context, NoteEditActivity.class);
        intent.putExtra(KEY_SAVEINSTANCE, note);
        intent.putParcelableArrayListExtra(ALL_NOTES_CODE, allNotes);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        
        initViews();
        setupViews();
        fillViews();
    }

    private void initViews(){
        titleEditText = findViewById(R.id.title_edit_view);
        detailEditText = findViewById(R.id.detail_edit_view);
        saveButton = findViewById(R.id.save_button);
    };

    private void setupViews() {

        saveButton.setOnClickListener(v -> {
            if (note == null) {
                note = new NoteEntity(
                        titleEditText.getText().toString(),
                        detailEditText.getText().toString()
                );
                if (allNotes == null) {
                    note.setId(0);
                } else {
                    note.setId(allNotes.size());
                }
                allNotes.add(note);
            } else {
                note.setTitle(titleEditText.getText().toString());
                note.setDetails(detailEditText.getText().toString());
            }
            notesRepo.saveNote(note, allNotes);
            finish();
        });
    }

    private void fillViews() {
        note = (NoteEntity) getIntent().getParcelableExtra(KEY_SAVEINSTANCE);
        allNotes = getIntent().getParcelableArrayListExtra(ALL_NOTES_CODE);
        if (note != null){
            titleEditText.setText(note.getTitle());
            detailEditText.setText(note.getDetails());
        } else{
            Log.d("mylogs", "activity null");
        }
    }
}