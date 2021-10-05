package ru.geekbrains.notebook.domain;

import androidx.annotation.Nullable;

public class NoteEntity {

    @Nullable
    private Integer id;
    private String title;
    private String details;

    public NoteEntity(String title, String details) {
        this.title = title;
        this.details = details;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
