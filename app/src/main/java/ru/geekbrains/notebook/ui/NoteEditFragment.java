package ru.geekbrains.notebook.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
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

public class NoteEditFragment extends Fragment {

    private EditText titleEditText = null;
    private EditText detailEditText = null;
    private Button saveButton = null;
    private NoteEntity note = null;
    private NotesRepo notesRepo = new NotesRepoImpl(getActivity());
    private ArrayList<NoteEntity> allNotes = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupViews();
        fillViews();
    }

    private void initViews(View view){
        titleEditText = view.findViewById(R.id.title_edit_view);
        detailEditText = view.findViewById(R.id.detail_edit_view);
        saveButton = view.findViewById(R.id.save_button);
    };

    private void setupViews() {

        saveButton.setOnClickListener(v -> {

        });
    }

    private void fillViews() {
//        if (Bundle == null) {
            Bundle args = getArguments();
            allNotes = args.getParcelableArrayList(ALL_NOTES_CODE);
            note = args.getParcelable(KEY_SAVEINSTANCE);
            titleEditText.setText(note.getTitle());
            detailEditText.setText(note.getDetails());
//        }

    }

    public static NoteEditFragment newInstance(NoteEntity note, ArrayList<NoteEntity> allNotes){
        NoteEditFragment noteEditFragment = new NoteEditFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ALL_NOTES_CODE, allNotes);
        bundle.putParcelable(KEY_SAVEINSTANCE, note);
        noteEditFragment.setArguments(bundle);
        return noteEditFragment;
    }

}