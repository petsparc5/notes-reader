package com.senior.accelerator.notes_reader.parser;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.File;
import java.io.IOException;

import org.easymock.EasyMockSupport;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senior.accelerator.notes_reader.dao.DesignPatternNote;
import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.dao.RegularNote;

/**
 *Test for {@JsonNoteParser}
 * @author Peter Takacs.
 */
public class JsonNoteParserTest extends EasyMockSupport {

    private static final String FILE_NAME = "fileName";
    private static final String MESSAGE = "message";
    private static final String ERROR_MESSAGE = "Parsing error occurred with file: fileName, message: message";
    private JsonNoteParser jsonNoteParser;
    private ObjectMapper objectMapper;

    @BeforeMethod
    public void init() {
        jsonNoteParser = new JsonNoteParser();
        objectMapper = createMock(ObjectMapper.class);
        jsonNoteParser.setObjectMapper(objectMapper);
        resetAll();
    }

    @Test
    public void testParseForARegularNote() throws IOException {
        //GIVEN
        Note exectedResult = createRegularNote();
        expect(objectMapper.readValue(anyObject(File.class), eq(RegularNote.class))).andReturn(createRegularNote());
        replayAll();
        //WHEN
        Note actualResult = jsonNoteParser.parse(FILE_NAME);
        //THEN
        verifyAll();
        assertThat(exectedResult, is(actualResult));
    }

    @Test
    public void testParseForADesignPatternNote() throws IOException {
        //GIVEN
        Note exectedResult = createDesignPatternsNote();
        expect(objectMapper.readValue(anyObject(File.class), eq(DesignPatternNote.class))).andReturn(createDesignPatternsNote());
        replayAll();
        //WHEN
        Note actualResult = jsonNoteParser.parse(FILE_NAME, DesignPatternNote.class);
        //THEN
        verifyAll();
        assertThat(exectedResult, is(actualResult));
    }

    @Test(expectedExceptions = JsonNoteParseException.class, expectedExceptionsMessageRegExp = ERROR_MESSAGE)
    public void testParseForARegularNoteShouldThrowJsonNoteParseExceptionWhenReadingFileFails() throws IOException {
        //GIVEN
        expect(objectMapper.readValue(anyObject(File.class), eq(RegularNote.class))).andThrow(new IOException(MESSAGE));
        replayAll();
        //WHEN
        jsonNoteParser.parse(FILE_NAME);
        //THEN
    }

    @Test(expectedExceptions = JsonNoteParseException.class)
    public void testParseForADesignPatterNoteShouldThrowJsonNoteParseExceptionWhenReadingFileFails() throws IOException {
        //GIVEN
        expect(objectMapper.readValue(anyObject(File.class), eq(DesignPatternNote.class))).andThrow(new IOException(MESSAGE));
        replayAll();
        //WHEN
        jsonNoteParser.parse(FILE_NAME, DesignPatternNote.class);
        //THEN
    }

    private RegularNote createRegularNote() {
        RegularNote note = new RegularNote(0, "Java", "Testing is awesome!");
        return note;
    }

    private DesignPatternNote createDesignPatternsNote() {
        DesignPatternNote note = new DesignPatternNote(0, "Template Method Pattern", "Children implement sections of the behaviour.");
        return note;
    }

}
