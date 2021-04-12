package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.List;

public class HomeForm {

    private List<File> files;
    private List<Note> notes;
    private List<Credential> credentials;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Credential> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credential> credentials) {
        this.credentials = credentials;
    }
}
