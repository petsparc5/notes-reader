package com.senior.accelerator.notes_reader.service;

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
import com.senior.accelerator.notes_reader.reader.NotesReader;

/**
 * Unit test for {@NotesDisplayService}.
 * @author Peter Takacs
 */
public class NotesDisplayServiceTest extends EasyMockSupport {

    private NotesDisplayService notesDisplayService;
    private NotesReader notesReader;

    @BeforeMethod
    public void init() {
        notesDisplayService = new NotesDisplayService();
        notesReader = createMock(NotesReader.class);
        notesDisplayService.setNotesReader(notesReader);
        resetAll();
    }

    @Test
    public void testAddMessage() {
        //GIVEN
        List<Note> notes = new ArrayList<>();
        notes.add(new DesignPatternNote(2, "patternName", "data"));
        expect(notesReader.readAll()).andReturn(notes);
        replayAll();
        //WHEN
        List<Note> actual = notesDisplayService.gatherNotes();
        //THEN
        verifyAll();
        assertThat(actual, is(notes));
    }
}
