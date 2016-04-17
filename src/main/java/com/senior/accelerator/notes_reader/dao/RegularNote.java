package com.senior.accelerator.notes_reader.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A data object for storing non-specialised data on a subject.
 * @author Peter Takacs
 */
public class RegularNote implements Note {

    @JsonProperty(value = "id")
    private int id;
    @JsonProperty(value = "subject")
    private String subject;
    @JsonProperty(value = "data")
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        RegularNote that = (RegularNote) o;

        if (id != that.id)
            return false;
        if (!subject.equals(that.subject))
            return false;
        return data.equals(that.data);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + subject.hashCode();
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("RegularNote %d: [subject='%s', data='%s']", id, subject, data);
    }

}
