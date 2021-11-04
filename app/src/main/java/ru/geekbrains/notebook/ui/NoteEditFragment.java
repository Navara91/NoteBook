package ru.geekbrains.notebook.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notebook.R;
import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.domain.DataTransferFromEditFragment;
import ru.geekbrains.notebook.impl.NotesRepoImpl;

import static ru.geekbrains.notebook.utils.Constants.ALL_NOTES_CODE;
import static ru.geekbrains.notebook.utils.Constants.KEY_SAVEINSTANCE;

public class NoteEditFragment extends Fragment {

    private EditText titleEditText = null;
    private EditText detailEditText = null;
    private Button saveButton = null;
    private NoteEntity note = null;
    private NotesRepo notesRepo = new NotesRepoImpl(getActivity());
    private ArrayList<NoteEntity> allNotes;
    private List<NoteEntity> data = new ArrayList<>();
    private DataTransferFromEditFragment editFragmentListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DataTransferFromEditFragment){
            editFragmentListener = (DataTransferFromEditFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSendDataFromEditFragment");
        }
    }

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

            String titleGetText = titleEditText.getText().toString();
            String detailGetText = detailEditText.getText().toString();
            notesRepo.saveNote(titleGetText, detailGetText, note, allNotes);
            editFragmentListener.reInitRecyclerAfterSavingData();
            closeFragment();
        });
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void fillViews() {
        Bundle args = getArguments();
        if (args != null) {
            note = args.getParcelable(KEY_SAVEINSTANCE);
            allNotes = args.getParcelableArrayList(ALL_NOTES_CODE);
            titleEditText.setText(note.getTitle());
            detailEditText.setText(note.getDetails());
        } else {
            titleEditText.setText("");
            detailEditText.setText("");
        }
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