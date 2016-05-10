package com.senior.accelerator.notes_reader.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.senior.accelerator.notes_reader.dao.Note;

/**
 * Filters a list of notes by its subject.
 * @author Peter Takacs
 */
@Component
public class SubjectFilter {

    /**
     * Generates a filtered notes based on a specified subject.
     * @param notes - list of notes that needs to be filtered.
     * @param subject - subject of a note that needs to filtered by.
     * @return - a filtered notes list.
     */
    public List<Note> filterNotesBySubject(List<Note> notes, String subject) {
        return notes.stream().filter(note -> note.getSubject().equals(subject)).collect(Collectors.toList());
    }
}
