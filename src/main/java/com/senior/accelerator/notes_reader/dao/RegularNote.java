package com.senior.accelerator.notes_reader.dao;

/**
 * A data object for storing non-specialised data on a subject.
 * @author Peter Takacs
 */
public class RegularNote implements Note {

    private int id;
    private String subject;
    private String data;

    public RegularNote(int id, String subject, String data) {
        this.id = id;
        this.subject = subject;
        this.data = data;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("RegularNote %d: [subject='%s', data='%s']", id, subject, data);
    }

}
