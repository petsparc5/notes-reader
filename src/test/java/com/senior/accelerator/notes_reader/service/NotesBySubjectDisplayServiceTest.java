package com.senior.accelerator.notes_reader.service;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.easymock.IMocksControl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.senior.accelerator.notes_reader.dao.DesignPatternNote;
import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.dao.RegularNote;
import com.senior.accelerator.notes_reader.reader.NotesReader;

/**
 * Unit test for {@NotesBySubjectDisplayService}.
 * @author Peter Takacs
 */
public class NotesBySubjectDisplayServiceTest extends EasyMockSupport {

    public static final String SUBJECT = "subject";
    private NotesBySubjectDisplayService notesBySubjectDisplayService;
    private IMocksControl control;
    private NotesReader notesReader;
    private SubjectFilter subjectFilter;

    @BeforeMethod
    public void init() {
        notesBySubjectDisplayService = new NotesBySubjectDisplayService();
        control = createControl();
        notesReader = control.createMock(NotesReader.class);
        subjectFilter = control.createMock(SubjectFilter.class);
        notesBySubjectDisplayService.setNotesReader(notesReader);
        notesBySubjectDisplayService.setSubjectFilter(subjectFilter);
    }

    @Test
    public void testGatherNotesForSubject() {
        //GIVEN
        List<Note> notes = createNotes();
        expect(notesReader.readAll()).andReturn(notes);
        List<Note> filteredNotes = createFilteredNotes();
        expect(subjectFilter.filterNotesBySubject(notes, SUBJECT)).andReturn(filteredNotes);
        control.replay();
        //WHEN
        List<Note> actual = notesBySubjectDisplayService.gatherNotesForSubject(SUBJECT);
        //THEN
        control.verify();
        assertThat(actual, is(filteredNotes));
    }

    private List<Note> createNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new DesignPatternNote(0, "patternName", "data"));
        notes.add(new RegularNote(1, SUBJECT, "data"));
        return notes;
    }

    private List<Note> createFilteredNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new RegularNote(1, SUBJECT, "data"));
        return notes;
    }
}
