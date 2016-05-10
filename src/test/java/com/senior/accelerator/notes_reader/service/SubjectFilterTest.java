package com.senior.accelerator.notes_reader.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.senior.accelerator.notes_reader.dao.DesignPatternNote;
import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.dao.RegularNote;

/**
 * Unit test for {@SubjectFilter}.
 * @author Peter Takacs
 */
public class SubjectFilterTest {

    public static final String JAVA = "Java";
    public static final String DATA = "data";
    public static final String ADAPTER = "Adapter";
    public static final String DECORATOR = "Decorator";
    public static final String TEMPLATE_METHOD = "Template Method";
    private SubjectFilter subjectFilter;

    @BeforeMethod
    public void init() {
        subjectFilter = new SubjectFilter();
    }

    @Test
    public void testFilterNotesBySubject() {
        //GIVEN
        List<Note> notes = createNotes();
        //WHEN
        List<Note> actual = subjectFilter.filterNotesBySubject(notes, "Design_Patterns");
        //THEN
        assertThat(actual.size(), is(3));
        assertTrue(actual.contains(createDesignPatterNote(ADAPTER)));
        assertTrue(actual.contains(createDesignPatterNote(DECORATOR)));
        assertTrue(actual.contains(createDesignPatterNote(TEMPLATE_METHOD)));
    }

    @Test
    public void testFilterNotesBySubjectWithNoValuesReturned() {
        //GIVEN
        List<Note> notes = createNotes();
        //WHEN
        List<Note> actual = subjectFilter.filterNotesBySubject(notes, "");
        //THEN
        assertThat(actual.size(), is(0));
    }

    private List<Note> createNotes() {
        List<Note> notes = new ArrayList<>();
        Note note0 = createDesignPatterNote(ADAPTER);
        Note note1 = new RegularNote(1, JAVA,  DATA);
        Note note2 = createDesignPatterNote(DECORATOR);
        Note note3 = new RegularNote(2, JAVA,  DATA);
        Note note4 = createDesignPatterNote(TEMPLATE_METHOD);
        notes.add(note0);
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);
        return notes;
    }

    private DesignPatternNote createDesignPatterNote(String patternName) {
        return new DesignPatternNote(0, patternName,  DATA);
    }
}
