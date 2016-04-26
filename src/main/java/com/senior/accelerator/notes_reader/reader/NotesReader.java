package com.senior.accelerator.notes_reader.reader;

import java.util.List;

import com.senior.accelerator.notes_reader.dao.Note;

/**
 * Reads in notes with a parser, and filters values based on subject.
 * @author Peter_Takacs
 */
public interface NotesReader {

    /**
     * Reads all the notes.
     * @return list of all the notes.
     */
    List<Note> readAll();
}
