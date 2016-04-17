package com.senior.accelerator.notes_reader.reader;

import static org.easymock.EasyMock.expect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMockSupport;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.dao.RegularNote;
import com.senior.accelerator.notes_reader.parser.JsonNoteParser;

/**
 * Tests {@JsonNotesReader}.
 * @author Peter_Takacs
 */
public class JsonNotesReaderTest extends EasyMockSupport {

    private static final String SUBJECT = "subject";
    public static final String FILE_NAME_1 = "fileName1";
    public static final String FILE_NAME_2 = "fileName2";
    private JsonNotesReader jsonNotesReader;
    private JsonNoteParser jsonNoteParser;
    private FileNameFactory fileNameFactory;

    @BeforeMethod
    public void init() {
        jsonNotesReader = new JsonNotesReader();
        jsonNoteParser = createMock(JsonNoteParser.class);
        fileNameFactory = createMock(FileNameFactory.class);
        jsonNotesReader.setJsonNoteParser(jsonNoteParser);
        jsonNotesReader.setFileNameFactory(fileNameFactory);
        resetAll();
    }

    @Test
    public void testReadForMultipleValues() {
        //GIVEN
        Note note1 = new RegularNote(1, SUBJECT, "data1");
        Note note2 = new RegularNote(2, SUBJECT, "data2");
        expect(fileNameFactory.createFileNamesBySubject(SUBJECT)).andReturn(createFileNames());
        expect(jsonNoteParser.parse(FILE_NAME_1)).andReturn(note1);
        expect(jsonNoteParser.parse(FILE_NAME_2)).andReturn(note2);
        replayAll();
        //WHEN
        List<Note> notes = jsonNotesReader.read(SUBJECT);
        //THEN
        verifyAll();
        assertThat(notes, contains(note1, note2));
    }

    @Test
    public void testReadForNoValues() {
        //GIVEN
        expect(fileNameFactory.createFileNamesBySubject(SUBJECT)).andReturn(new ArrayList<String>());
        replayAll();
        //WHEN
        List<Note> notes = jsonNotesReader.read(SUBJECT);
        //THEN
        verifyAll();
        assertThat(notes.isEmpty(), is(true));
    }

    @Test
    public void testReadAllForMultipleValues() {
        //GIVEN
        Note note1 = new RegularNote(1, SUBJECT, "data1");
        Note note2 = new RegularNote(2, SUBJECT, "data2");
        expect(fileNameFactory.createAllFileNames()).andReturn(createFileNames());
        expect(jsonNoteParser.parse(FILE_NAME_1)).andReturn(note1);
        expect(jsonNoteParser.parse(FILE_NAME_2)).andReturn(note2);
        replayAll();
        //WHEN
        List<Note> notes = jsonNotesReader.readAll();
        //THEN
        verifyAll();
        assertThat(notes, contains(note1, note2));
    }

    @Test
    public void testReadAllForNoValues() {
        //GIVEN
        expect(fileNameFactory.createAllFileNames()).andReturn(new ArrayList<String>());
        replayAll();
        //WHEN
        List<Note> notes = jsonNotesReader.readAll();
        //THEN
        verifyAll();
        assertThat(notes.isEmpty(), is(true));
    }

    private List<String> createFileNames() {
        List<String> fileNames = new ArrayList<>();
        fileNames.add(FILE_NAME_1);
        fileNames.add(FILE_NAME_2);
        return fileNames;
    }
}
