package com.senior.accelerator.notes_reader.facade;

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
import com.senior.accelerator.notes_reader.service.NotesBySubjectDisplayService;
import com.senior.accelerator.notes_reader.service.NotesDisplayService;

/**
 * Unit test for {@NotesDisplayFacade}.
 * @author Peter Takacs
 */
public class NotesDisplayFacadeTest extends EasyMockSupport {

    public static final String SUBJECT = "Subject";
    private NotesDisplayFacade notesDisplayFacade;
    private IMocksControl control;
    private NotesDisplayService notesDisplayService;
    private NotesBySubjectDisplayService notesBySubjectDisplayService;

    @BeforeMethod
    public void init() {
        notesDisplayFacade = new NotesDisplayFacade();
        control = createControl();
        notesDisplayService = control.createMock(NotesDisplayService.class);
        notesBySubjectDisplayService = control.createMock(NotesBySubjectDisplayService.class);
        notesDisplayFacade.setNotesDisplayService(notesDisplayService);
        notesDisplayFacade.setNotesBySubjectDisplayService(notesBySubjectDisplayService);
    }

    @Test
    public void testGetAllNotes() {
        //GIVEN
        List<Note> notes = new ArrayList<>();
        notes.add(new DesignPatternNote(2, "patternName", "data"));
        expect(notesDisplayService.gatherNotes()).andReturn(notes);
        control.replay();
        //WHEN
        List<Note> actual = notesDisplayFacade.getAllNotes();
        //THEN
        control.verify();
        assertThat(actual, is(notes));
    }

    @Test
    public void tesGetAllNotesForSubject() {
        //GIVEN
        List<Note> notes = new ArrayList<>();
        notes.add(new DesignPatternNote(2, "patternName", "data"));
        expect(notesBySubjectDisplayService.gatherNotesForSubject(SUBJECT)).andReturn(notes);
        control.replay();
        //WHEN
        List<Note> actual = notesDisplayFacade.getAllNotesForSubject(SUBJECT);
        //THEN
        control.verify();
        assertThat(actual, is(notes));
    }
}
