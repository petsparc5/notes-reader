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
 * Test for {@NotesDisplayForASubjectController}.
 * @author Peter_Takacs
 */
public class NotesDisplayForASubjectControllerTest extends EasyMockSupport {

    private static final String ALL_NOTES_BY_SUBJECT = "allNotesBySubject";
    public static final String SUBJECT = "SUBJECT";
    private NotesDisplayForASubjectController notesDisplayForASubjectController;
    private NotesDisplayFacade notesDisplayFacade;

    @BeforeMethod
    public void init() {
        notesDisplayForASubjectController = new NotesDisplayForASubjectController();
        notesDisplayFacade = createMock(NotesDisplayFacade.class);
        notesDisplayForASubjectController.setNotesDisplayFacade(notesDisplayFacade);
        resetAll();
    }

    @Test
    public void testAllNotesForASubject() {
        //GIVEN
        ModelMap map = new ModelMap();
        List<Note> notes = createNotes();
        expect(notesDisplayFacade.getAllNotesForSubject(SUBJECT)).andReturn(notes);
        replayAll();
        //WHEN
        String actual = notesDisplayForASubjectController.allNotesForASubject(SUBJECT, map);
        //THEN
        verifyAll();
        assertThat(ALL_NOTES_BY_SUBJECT, is(actual));
        assertTrue(map.containsAttribute(ALL_NOTES_BY_SUBJECT));
        assertThat((List<Note>) map.get(ALL_NOTES_BY_SUBJECT), is(notes));
    }

    private List<Note> createNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new DesignPatternNote(2, "patternName", "data"));
        return notes;
    }
}
