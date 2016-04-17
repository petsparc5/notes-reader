package com.senior.accelerator.notes_reader.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.parser.JsonNoteParser;

/**
 * Reads in json files and with a parser, it parses in the notes.
 * @author Peter_Takacs
 */
@Component
public class JsonNotesReader implements NotesReader  {

    @Autowired
    private JsonNoteParser jsonNoteParser;
    @Autowired
    private FileNameFactory fileNameFactory;

    @Override
    public List<Note> read(String subject) {
        List<Note> notes = new ArrayList<>();
        for (String fileName : fileNameFactory.createFileNamesBySubject(subject)) {
            Note note = jsonNoteParser.parse(fileName);
            notes.add(note);
        }
        return notes;
    }

    @Override
    public List<Note> readAll() {
        List<Note> notes = new ArrayList<>();
        for (String fileName : fileNameFactory.createAllFileNames()) {
            Note note = jsonNoteParser.parse(fileName);
            notes.add(note);
        }
        return notes;
    }

    protected void setFileNameFactory(FileNameFactory fileNameFactory) {
        this.fileNameFactory = fileNameFactory;
    }

    protected void setJsonNoteParser(JsonNoteParser jsonNoteParser) {
        this.jsonNoteParser = jsonNoteParser;
    }
}
