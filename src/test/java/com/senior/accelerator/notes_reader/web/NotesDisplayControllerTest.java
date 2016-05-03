package com.senior.accelerator.notes_reader.web;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.springframework.ui.ModelMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.senior.accelerator.notes_reader.dao.DesignPatternNote;
import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.facade.NotesDisplayFacade;

/**
 * Test for {@NotesDisplayController}.
 * @author Peter_Takacs
 */
public class NotesDisplayControllerTest extends EasyMockSupport {

    private static final String ALL_NOTES = "allNotes";
    private NotesDisplayController notesDisplayController;
    private NotesDisplayFacade notesDisplayFacade;

    @BeforeMethod
    public void init() {
        notesDisplayController = new NotesDisplayController();
        notesDisplayFacade = createMock(NotesDisplayFacade.class);
        notesDisplayController.setNotesDisplayFacade(notesDisplayFacade);
        resetAll();
    }

    @Test
    public void testAllNotes() {
        //GIVEN
        ModelMap map = new ModelMap();
        List<Note> notes = createNotes();
        expect(notesDisplayFacade.getAllNotes()).andReturn(notes);
        replayAll();
        //WHEN
        String actual = notesDisplayController.allNotes(map);
        //THEN
        verifyAll();
        assertThat(ALL_NOTES, is(actual));
        assertTrue(map.containsAttribute(ALL_NOTES));
        assertThat((List<Note>) map.get(ALL_NOTES), is(notes));
    }

    private List<Note> createNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new DesignPatternNote(2, "patternName", "data"));
        return notes;
    }
}
