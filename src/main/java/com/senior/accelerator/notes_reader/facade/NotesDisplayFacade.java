package com.senior.accelerator.notes_reader.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

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
     * Adds all the notes gathered by the notesDisplayService to the map.
     * @param map where all the notes need to be stored.
     */
    public void addAllNotes(ModelMap map) {
        map.put(ALL_NOTES, notesDisplayService.gatherNotes());
    }

    protected void setNotesDisplayService(NotesDisplayService notesDisplayService) {
        this.notesDisplayService = notesDisplayService;
    }
}
