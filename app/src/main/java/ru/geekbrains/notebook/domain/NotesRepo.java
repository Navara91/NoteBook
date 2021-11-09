package ru.geekbrains.notebook.domain;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public interface NotesRepo {
    ArrayList<NoteEntity> getNotes();
    void setAllNotes(ArrayList<NoteEntity> allNotes);
    void setNoteContent(NoteEntity note);
    @Nullable
    Integer createNote(NoteEntity note/*, ArrayList<NoteEntity> allNotes*/);
    boolean deleteNote(int id);
    boolean saveNote(String titleGetText, String detailGetText, NoteEntity note, ArrayList<NoteEntity> allNotes);
}
