package ru.geekbrains.notebook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;

public class NoteEditActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        titleEditText = findViewById(R.id.title_edit_view);
        detailEditText = findViewById(R.id.detail_edit_view);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(v -> {
            NoteEntity noteEntity = new NoteEntity(
                    titleEditText.getText().toString(),
                    detailEditText.getText().toString()
            );
            //todo???
        });
    }
}