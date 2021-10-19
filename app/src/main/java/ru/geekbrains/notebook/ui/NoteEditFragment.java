package ru.geekbrains.notebook.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.geekbrains.notebook.R;

public class NoteEditFragment extends Fragment {

    public NoteEditFragment() {
        // Required empty public constructor
    }

    public static NoteEditFragment newInstance() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_edit, container, false);
    }
}