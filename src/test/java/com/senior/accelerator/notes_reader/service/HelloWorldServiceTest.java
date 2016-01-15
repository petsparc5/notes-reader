package com.senior.accelerator.notes_reader.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.springframework.ui.ModelMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.dao.NoteRepository;

/**
 * Unit test for {@HelloWorldService}.
 * Created by Peter_Takacs on 14/01/2016.
 */
public class HelloWorldServiceTest extends EasyMockSupport {

    private static final String MESSAGE = "message";
    private static final String MESSAGE_VALUE = "Hello World, Hello Master Peter!";
    public static final String SPRING = "spring";
    public static final String SPRING_NOTES = "springNotes";
    public static final String NOTES = "notes";

    private HelloWorldService helloWorldService;
    private NoteRepository noteRepository;

    @BeforeMethod
    public void init() {
        helloWorldService = new HelloWorldService();
        noteRepository = createMock(NoteRepository.class);
        helloWorldService.setNoteRepository(noteRepository);
        resetAll();
    }

    @Test
    public void testAddMessage() {
        //GIVEN
        ModelMap map = new ModelMap();
        //WHEN
        helloWorldService.addMessage(map);
        //THEN
        assertTrue(map.containsAttribute(MESSAGE));
        assertThat((String) map.get(MESSAGE), is(MESSAGE_VALUE));
    }

    @Test
    public void testPlayWithDB() {
        //GIVEN
        ModelMap map = new ModelMap();
        expect(noteRepository.save(isA(Note.class))).andReturn(createEmptyNote()).anyTimes();
        List<Note> oneNote = createNotesListWithOneElement();
        List<Note> twoNotes = createNotesListWithTwoElement();
        expect(noteRepository.findAll()).andReturn(twoNotes);
        expect(noteRepository.findBySubject(SPRING)).andReturn(oneNote);
        replayAll();
        //WHEN
        helloWorldService.playWithDB(map);
        //THEN
        verifyAll();
        assertTrue(map.containsAttribute(NOTES));
        assertTrue(map.containsAttribute(SPRING_NOTES));
        assertThat((List<Note>) map.get(NOTES), is(twoNotes));
        assertThat((List<Note>) map.get(SPRING_NOTES), is(oneNote));
    }

    private Note createEmptyNote() {
        return new Note("", "");
    }

    private List<Note> createNotesListWithOneElement() {
        Note note1 = new Note("1", "one");
        return createNotes(note1);
    }

    private List<Note> createNotesListWithTwoElement() {
        Note note1 = new Note("1", "one");
        Note note2 = new Note("2", "two");
        return createNotes(note1, note2);
    }

    private List<Note> createNotes(Note... notes) {
        List<Note> notesList = new ArrayList<>();
        for (Note note : notes) {
            notesList.add(note);
        }
        return notesList;
    }

}
