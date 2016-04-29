package com.senior.accelerator.notes_reader.web;

import static org.easymock.EasyMock.expectLastCall;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.easymock.EasyMockSupport;
import org.springframework.ui.ModelMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    public void testHelloWorld() {
        //GIVEN
        ModelMap map = new ModelMap();
        notesDisplayFacade.addAllNotes(map);
        expectLastCall();
        replayAll();
        //WHEN
        String actual = notesDisplayController.allNotes(map);
        //THEN
        verifyAll();
        assertThat(ALL_NOTES, is(actual));
    }
}
