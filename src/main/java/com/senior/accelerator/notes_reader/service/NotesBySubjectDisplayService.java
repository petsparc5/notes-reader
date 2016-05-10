package com.senior.accelerator.notes_reader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.reader.NotesReader;

/**
 * Class Responsible for gathering notes information from the reader and filtering by a subject.
 * @author Peter Takacs
 */
@Component
public class NotesBySubjectDisplayService {

    @Autowired
    private NotesReader notesReader;
    @Autowired
    private SubjectFilter subjectFilter;


    /**
     * Gathers all the notes for a given subject.
     * @param subject - the subject for which all notes must be gathered.
     * @return list of all the notes for a provided subject.
     */
    public List<Note> gatherNotesForSubject(String subject) {
        List<Note> notes = notesReader.readAll();
        return subjectFilter.filterNotesBySubject(notes, subject);
    }

    protected void setNotesReader(NotesReader notesReader) {
        this.notesReader = notesReader;
    }

    protected void setSubjectFilter(SubjectFilter subjectFilter) {
        this.subjectFilter = subjectFilter;
    }
}
