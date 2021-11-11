package ru.geekbrains.notebook.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.impl.NotesRepoImpl;

import static ru.geekbrains.notebook.utils.Constants.ALL_NOTES_CODE;
import static ru.geekbrains.notebook.utils.Constants.KEY_SAVEINSTANCE;

public class NoteEditFragment extends Fragment {

    private static final String TAG = "@@@";
    private EditText titleEditText = null;
    private EditText detailEditText = null;
    private Button saveButton = null;
    private NoteEntity note = null;
    private NotesRepo notesRepo = new NotesRepoImpl();
    private ArrayList<NoteEntity> allNotes;
    private MainActivity mainActivity = null;

    @Override
    public void onAttach(Context context) {
        mainActivity = (MainActivity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated EditFragment called");

        initViews(view);
        setupViews();
        fillViews();
    }

    private void initViews(View view) {
        titleEditText = view.findViewById(R.id.title_edit_view);
        detailEditText = view.findViewById(R.id.detail_edit_view);
        saveButton = view.findViewById(R.id.save_button);
    }

    private void setupViews() {
        saveButton.setOnClickListener(v -> {
            note.setTitle(titleEditText.getText().toString());
            note.setDetails(detailEditText.getText().toString());
            if (note.getId() != null) {
                notesRepo.setAllNotes(allNotes);
                mainActivity.setNoteRepo(notesRepo);
            } else {
                note.setId(allNotes.size());
                allNotes.add(note);
                notesRepo.setAllNotes(allNotes);
                mainActivity.setNoteRepo(notesRepo);
            }
            closeFragment();
        });
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void fillViews() {
        Bundle args = getArguments();
        allNotes = args.getParcelableArrayList(ALL_NOTES_CODE);
        note = args.getParcelable(KEY_SAVEINSTANCE);
        if (note != null) {
            titleEditText.setText(note.getTitle());
            detailEditText.setText(note.getDetails());
        } else {
            note = new NoteEntity("", "");
            titleEditText.setText(note.getTitle());
            detailEditText.setText(note.getDetails());
        }

    }

    public static NoteEditFragment newInstance(NoteEntity note, ArrayList<NoteEntity> allNotes) {
        NoteEditFragment noteEditFragment = new NoteEditFragment();
        if (allNotes != null){
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(ALL_NOTES_CODE, allNotes);
            if (note != null)
                bundle.putParcelable(KEY_SAVEINSTANCE, note);
            noteEditFragment.setArguments(bundle);
        }
        return noteEditFragment;
    }
}