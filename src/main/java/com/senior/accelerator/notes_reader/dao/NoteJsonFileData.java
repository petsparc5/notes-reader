package com.senior.accelerator.notes_reader.dao;

/**
 * Stores the information about a json note.
 * @author Peter Takacs
 */
public class NoteJsonFileData {

    private String filename;
    private Class noteType;

    /**
     * Constructor with all the parameters.
     * @param filename - name of the file.
     * @param noteType - type of the note.
     */
    public NoteJsonFileData(String filename, Class noteType) {
        this.filename = filename;
        this.noteType = noteType;
    }

    public String getFilename() {
        return filename;
    }

    public Class getNoteType() {
        return noteType;
    }

}
