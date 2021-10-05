package ru.geekbrains.notebook.domain;

import java.util.List;

public interface NotesRepo {
    List<NoteEntity> getNotes();
    boolean createNote(NoteEntity note);
    boolean deleteNote(int id);
    boolean updateNote(int id, NoteEntity note);
}
