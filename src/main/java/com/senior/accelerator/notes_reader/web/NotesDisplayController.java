package com.senior.accelerator.notes_reader.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.senior.accelerator.notes_reader.facade.NotesDisplayFacade;

/**
 * Controller for displaying all the notes.
 * @author Peter Takacs
 */
@Controller
public class NotesDisplayController {

    @Autowired
    private NotesDisplayFacade notesDisplayFacade;

    /**
     * Display all the notes.
     * @param map - Modelmap
     * @return with allNotes.
     */
    @RequestMapping(value = "/allNotes", method = RequestMethod.GET)
    public String allNotes(ModelMap map) {
        notesDisplayFacade.addAllNotes(map);
        return "allNotes";
    }

    protected void setNotesDisplayFacade(NotesDisplayFacade notesDisplayFacade) {
        this.notesDisplayFacade = notesDisplayFacade;
    }
}
