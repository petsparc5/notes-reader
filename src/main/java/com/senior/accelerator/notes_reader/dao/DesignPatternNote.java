package com.senior.accelerator.notes_reader.dao;

/**
 * A data object for storing data on a Design Patterns.
 * @author Peter Takacs
 */
public class DesignPatternNote implements Note {

    private static final String DESIGN_PATTERN = "Design Patterns";

    private int id;
    private String patternName;
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
    public String toString() {
        return String.format("DesignPatternNote %d: [Pattern Name='%s', data='%s']", id, patternName, data);
    }
}
