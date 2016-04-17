package com.senior.accelerator.notes_reader.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A data object for storing data on a Design Patterns.
 * @author Peter Takacs
 */
@JsonIgnoreProperties({"subject"})
public class DesignPatternNote implements Note {

    private static final String DESIGN_PATTERN = "Design Patterns";

    @JsonProperty(value = "id")
    private int id;
    @JsonProperty(value = "patternName")
    private String patternName;
    @JsonProperty(value = "data")
    private String data;

    public DesignPatternNote(int id, String patternName, String data) {
        this.id = id;
        this.patternName = patternName;
        this.data = data;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getSubject() {
        return DESIGN_PATTERN;
    }

    public String getPatternName() {
        return patternName;
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

        DesignPatternNote that = (DesignPatternNote) o;

        if (id != that.id)
            return false;
        if (!patternName.equals(that.patternName))
            return false;
        return data.equals(that.data);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + patternName.hashCode();
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("DesignPatternNote %d: [Pattern Name='%s', data='%s']", id, patternName, data);
    }
}
