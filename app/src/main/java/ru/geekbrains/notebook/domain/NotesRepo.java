package ru.geekbrains.notebook.domain;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface NotesRepo {
    ArrayList<NoteEntity> getNotes();
    void setAllNotes(ArrayList<NoteEntity> allNotes);
    void setNoteContent(NoteEntity note);
    @Nullable
    Integer createNote(NoteEntity note);
    boolean deleteNote(int id);
    boolean saveNote(String titleGetText, String detailGetText, NoteEntity note, ArrayList<NoteEntity> allNotes);
}
