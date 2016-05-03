package com.senior.accelerator.notes_reader.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.service.NotesDisplayService;

/**
 * Facade for displaying different Notes.
 * @author Peter Takacs
 */
@Component
public class NotesDisplayFacade {

    private static final String ALL_NOTES = "allNotes";

    @Autowired
    private NotesDisplayService notesDisplayService;

    /**
     * returns all the notesDisplayService.
     */
    public List<Note> getAllNotes() {
        return notesDisplayService.gatherNotes();
    }

    protected void setNotesDisplayService(NotesDisplayService notesDisplayService) {
        this.notesDisplayService = notesDisplayService;
    }
}
