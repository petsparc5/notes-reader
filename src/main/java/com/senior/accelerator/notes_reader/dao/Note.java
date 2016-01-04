package com.senior.accelerator.notes_reader.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A data object for storing data on a subject.
 * @author Peter Takacs
 *
 */
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String subject;
    private String data;

    public Note(String subject, String data) {
        super();
        this.subject = subject;
        this.data = data;
    }

    public String getSubject() {
        return subject;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("Note[id=%d, subject='%s', data='%s']", id, subject, data);
    }

}
