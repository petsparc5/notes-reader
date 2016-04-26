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

import com.senior.accelerator.notes_reader.dao.DesignPatternNote;
import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.dao.NoteJsonFileData;
import com.senior.accelerator.notes_reader.dao.RegularNote;
import com.senior.accelerator.notes_reader.parser.JsonNoteParser;

/**
 * Tests {@JsonNotesReader}.
 * @author Peter_Takacs
 */
public class JsonNotesReaderTest extends EasyMockSupport {

    public static final String SUBJECT = "subject";
    public static final String FILE_NAME_1 = "fileName1";
    public static final String FILE_NAME_2 = "fileName2";
    public static final String PATTERN_NAME = "Template Method Pattern";
    public static final String DATA = "data";
    private JsonNotesReader jsonNotesReader;
    private JsonNoteParser jsonNoteParser;

    @BeforeMethod
    public void init() {
        jsonNotesReader = new JsonNotesReader();
        jsonNoteParser = createMock(JsonNoteParser.class);
        jsonNotesReader.setJsonNoteParser(jsonNoteParser);
        resetAll();
    }

    @Test
    public void testReadAllForMultipleValues() {
        //GIVEN
        jsonNotesReader.setNoteJsonFileDataList(createNoteJsonFileDataList());
        Note note1 = new RegularNote(1, SUBJECT, DATA);
        Note note2 = new DesignPatternNote(2, PATTERN_NAME, DATA);
        expect(jsonNoteParser.parse(FILE_NAME_1, RegularNote.class)).andReturn(note1);
        expect(jsonNoteParser.parse(FILE_NAME_2, DesignPatternNote.class)).andReturn(note2);
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
        jsonNotesReader.setNoteJsonFileDataList(new ArrayList<NoteJsonFileData>());
        replayAll();
        //WHEN
        List<Note> notes = jsonNotesReader.readAll();
        //THEN
        verifyAll();
        assertThat(notes.isEmpty(), is(true));
    }

    private List<NoteJsonFileData> createNoteJsonFileDataList() {
        List<NoteJsonFileData> noteJsonFileDataList = new ArrayList<>();
        noteJsonFileDataList.add(new NoteJsonFileData(FILE_NAME_1, RegularNote.class));
        noteJsonFileDataList.add(new NoteJsonFileData(FILE_NAME_2, DesignPatternNote.class));
        return noteJsonFileDataList;
    }
}
