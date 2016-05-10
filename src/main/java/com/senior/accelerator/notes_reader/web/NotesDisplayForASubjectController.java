package com.senior.accelerator.notes_reader.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.senior.accelerator.notes_reader.facade.NotesDisplayFacade;

/**
 * Controller for displaying all the notes.
 * @author Peter Takacs
 */
@Controller
public class NotesDisplayForASubjectController {

    private static final String ALL_NOTES_BY_SUBJECT = "allNotesBySubject";
    @Autowired
    private NotesDisplayFacade notesDisplayFacade;

    /**
     * Display all the notes for a given subject.
     * @param subject - subject for which we need all the notes.
     * @param map - Modelmap
     * @return with allNotes.
     */
    @RequestMapping(value = "/subject/{subjectID}", method = RequestMethod.GET)
    public String allNotesForASubject(@PathVariable("subjectID") String subject, ModelMap map) {
        map.put(ALL_NOTES_BY_SUBJECT, notesDisplayFacade.getAllNotesForSubject(subject));
        return ALL_NOTES_BY_SUBJECT;
    }

    protected void setNotesDisplayFacade(NotesDisplayFacade notesDisplayFacade) {
        this.notesDisplayFacade = notesDisplayFacade;
    }
}
