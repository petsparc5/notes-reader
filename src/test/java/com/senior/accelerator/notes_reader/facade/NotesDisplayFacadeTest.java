package com.senior.accelerator.notes_reader.facade;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.senior.accelerator.notes_reader.dao.DesignPatternNote;
import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.service.NotesDisplayService;

/**
 * Unit test for {@NotesDisplayFacade}.
 * @author Peter Takacs
 */
public class NotesDisplayFacadeTest extends EasyMockSupport {

    private NotesDisplayFacade notesDisplayFacade;
    private NotesDisplayService notesDisplayService;

    @BeforeMethod
    public void init() {
        notesDisplayFacade = new NotesDisplayFacade();
        notesDisplayService = createMock(NotesDisplayService.class);
        notesDisplayFacade.setNotesDisplayService(notesDisplayService);
        resetAll();
    }

    @Test
    public void testGetAllNotes() {
        //GIVEN
        List<Note> notes = new ArrayList<>();
        notes.add(new DesignPatternNote(2, "patternName", "data"));
        expect(notesDisplayService.gatherNotes()).andReturn(notes);
        replayAll();
        //WHEN
        List<Note> actual = notesDisplayFacade.getAllNotes();
        //THEN
        verifyAll();
        assertThat(actual, is(notes));
    }
}
