package com.senior.accelerator.notes_reader.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.service.NotesBySubjectDisplayService;
import com.senior.accelerator.notes_reader.service.NotesDisplayService;

/**
 * Facade for displaying different Notes.
 * @author Peter Takacs
 */
@Component
public class NotesDisplayFacade {

    @Autowired
    private NotesDisplayService notesDisplayService;
    @Autowired
    private NotesBySubjectDisplayService notesBySubjectDisplayService;

    /**
     * Generates all the existing notes in a list.
     * @return all the notes.
     */
    public List<Note> getAllNotes() {
        return notesDisplayService.gatherNotes();
    }

    /**
     * Generates all the existing notes in a list for a given subject.
     * @param subject - the subject for which all notes must be gathered.
     * @return list of all the notes for a provided subject.
     */
    public List<Note> getAllNotesForSubject(String subject) {
        return notesBySubjectDisplayService.gatherNotesForSubject(subject);
    }

    protected void setNotesDisplayService(NotesDisplayService notesDisplayService) {
        this.notesDisplayService = notesDisplayService;
    }

    protected void setNotesBySubjectDisplayService(NotesBySubjectDisplayService notesBySubjectDisplayService) {
        this.notesBySubjectDisplayService = notesBySubjectDisplayService;
    }
}
