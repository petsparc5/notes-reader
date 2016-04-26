package com.senior.accelerator.notes_reader.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.dao.NoteJsonFileData;
import com.senior.accelerator.notes_reader.parser.JsonNoteParser;

/**
 * Reads in json files and with a parser, it parses in the notes.
 * @author Peter_Takacs
 */
@Component
public class JsonNotesReader implements NotesReader  {

    @Autowired
    private JsonNoteParser jsonNoteParser;
    //TODO: Investigate what happens if no NoteJsonFileData bean definitions exist!
    @Autowired
    private List<NoteJsonFileData> noteJsonFileDataList;

    @Override
    public List<Note> readAll() {
        List<Note> notes = new ArrayList<>();
        for (NoteJsonFileData noteJsonFileData : noteJsonFileDataList) {
            Note note = jsonNoteParser.parse(noteJsonFileData.getFilename(), noteJsonFileData.getNoteType());
            notes.add(note);
        }
        return notes;
    }

    protected void setNoteJsonFileDataList(List<NoteJsonFileData> noteJsonFileDataList) {
        this.noteJsonFileDataList = noteJsonFileDataList;
    }

    protected void setJsonNoteParser(JsonNoteParser jsonNoteParser) {
        this.jsonNoteParser = jsonNoteParser;
    }
}
