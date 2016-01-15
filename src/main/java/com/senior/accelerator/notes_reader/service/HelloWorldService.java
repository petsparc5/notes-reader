package com.senior.accelerator.notes_reader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.dao.NoteRepository;

/**
 * Service for Hello World.
 * @author Peter Takacs
 *
 */
@Service
public class HelloWorldService {

    private static final String MESSAGE = "message";
    private static final String MESSAGE_VALUE = "Hello World, Hello Master Peter!";
    public static final String SPRING = "spring";
    public static final String SPRING_NOTES = "springNotes";
    public static final String NOTES = "notes";

    //@Autowired
    private NoteRepository noteRepository;

    /**
     * Populates the ModelMap with a Hello World message.
     * @param map - Modelmap
     */
    public void addMessage(ModelMap map) {
        map.put(MESSAGE, MESSAGE_VALUE);
    }

    public void playWithDB(ModelMap map) {
        noteRepository.save(new Note("spring", "This is hard"));
        noteRepository.save(new Note("jpa", "I have no idea what I'm doing"));
        noteRepository.save(new Note("log", "This seems a little easier"));
        noteRepository.save(new Note("spring", "Not this again!! :("));
        map.put(NOTES, noteRepository.findAll());
        map.put(SPRING_NOTES, noteRepository.findBySubject(SPRING));
    }

    protected void setNoteRepository(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
}
