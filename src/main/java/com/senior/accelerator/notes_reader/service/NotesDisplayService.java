package com.senior.accelerator.notes_reader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.reader.NotesReader;

/**
 * Class Responsible for gathering notes information from the reader.
 * @author Peter Takacs
 */
@Component
public class NotesDisplayService {

    @Autowired
    private NotesReader notesReader;

    /**
     * Using the notes reader, gather all the notes.
     * @return all the notes in a list from the reader.
     */
    public List<Note> gatherNotes() {
        return notesReader.readAll();
    }

    protected void setNotesReader(NotesReader notesReader) {
        this.notesReader = notesReader;
    }
}
