package ru.geekbrains.notebook.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import ru.geekbrains.notebook.domain.NoteEntity;
import ru.geekbrains.notebook.domain.NotesRepo;
import ru.geekbrains.notebook.ui.MainActivity;
import ru.geekbrains.notebook.ui.NoteEditFragment;

import static ru.geekbrains.notebook.utils.Constants.ALL_NOTES_CODE;
import static ru.geekbrains.notebook.utils.Constants.KEY_SAVEINSTANCE;

public class NotesRepoImpl implements NotesRepo {
    private ArrayList<NoteEntity> allNotes = new ArrayList<>();
    private int counter = 0;
    private Activity activity = null;

    public NotesRepoImpl(Activity mainActivity) {
        this.activity = mainActivity;
    }

    @Override
    public ArrayList<NoteEntity> getNotes() {
//        activity.setData(allNotes);
        return new ArrayList<>(allNotes);
    }

    @Override
    public void setAllNotes(ArrayList<NoteEntity> allNotes) {
        this.allNotes = allNotes;
    }

    @Override
    public void setNoteContent(NoteEntity note) {
        allNotes.get(note.getId()).setTitle(note.getTitle());
        allNotes.get(note.getId()).setDetails(note.getDetails());
    }

    @Nullable
    @Override
    public Integer createNote(NoteEntity note) {
        int newId = counter++;
        note.setId(newId);
        allNotes.add(note);
        return newId;
    }

    @Override
    public boolean deleteNote(int id) {
        for (int i = 0; i < allNotes.size(); i++) {
            if (allNotes.get(i).getId() == id){
                allNotes.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean saveNote(NoteEntity note, ArrayList<NoteEntity> allNotes) {

        Intent intent = new Intent();
        intent.putExtra(KEY_SAVEINSTANCE, note);
        intent.putParcelableArrayListExtra(ALL_NOTES_CODE, allNotes);
        activity.setResult(Activity.RESULT_OK, intent);
        return true;
    }
}
