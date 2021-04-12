package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NoteMapper noteMapper;

    public List<Note> getNotesByUserId(int userId) {
        return noteMapper.getNotesByUserId(userId);
    }

    public String store(String noteTitle, String noteDescription, int userid) {
        String validationMsg = validateNoteTitle(noteTitle);
        if (!validationMsg.isEmpty()) return validationMsg;

        Note note = new Note(
                noteTitle,
                noteDescription,
                userid);

        noteMapper.insert(note);
        return "";
    }

    public String editNote(int noteId, String noteTitle, String noteDescription) {
        String validationMsg = validateNoteTitle(noteTitle);
        if (!validationMsg.isEmpty()) return validationMsg;

        Note note = new Note(noteId,
                noteTitle,
                noteDescription);

        noteMapper.edit(note);
        return "";
    }

    public void deleteNote(int noteId, int userid) {
        noteMapper.deleteNoteByNoteId(noteId, userid);
    }

    private String validateNoteTitle(String noteTitle) {
        if (noteTitle.trim().isEmpty()) {
            return "Note title is required";
        }
        if (this.noteMapper.getNoteIdByTitle(noteTitle) != null) {
            return "This note added before";
        }
        return "";
    }

}
