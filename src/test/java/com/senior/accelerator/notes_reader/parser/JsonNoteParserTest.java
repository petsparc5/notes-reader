package com.senior.accelerator.notes_reader.parser;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.function.Supplier;

import org.easymock.EasyMockSupport;
import org.easymock.IMocksControl;
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

    public static final String URL = "URL";
    private static final String FILE_NAME = "fileName";
    private static final String MESSAGE = "message";
    private static final String ERROR_MESSAGE = "Parsing error occurred with file: fileName, message: message";
    private JsonNoteParser jsonNoteParser;
    private IMocksControl control;
    private ObjectMapper objectMapper;
    private Supplier<ClassLoader> classLoaderSupplier;
    private ClassLoader classLoader;
    private InputStream resourceAsStream;

    @BeforeMethod
    public void init() {
        jsonNoteParser = new JsonNoteParser();
        control = createControl();
        objectMapper = control.createMock(ObjectMapper.class);
        classLoaderSupplier = control.createMock(Supplier.class);
        classLoader = control.createMock(ClassLoader.class);
        resourceAsStream = control.createMock(InputStream.class);
        jsonNoteParser.setObjectMapper(objectMapper);
        jsonNoteParser.setClassLoaderSupplier(classLoaderSupplier);
        resetAll();
    }

    @Test
    public void testParseForARegularNote() throws IOException {
        //GIVEN
        Note expectedResult = createRegularNote();
        expect(classLoaderSupplier.get()).andReturn(classLoader);
        expect(classLoader.getResourceAsStream(FILE_NAME)).andReturn(resourceAsStream);
        expect(objectMapper.readValue(resourceAsStream, RegularNote.class)).andReturn(createRegularNote());
        replayAll();
        //WHEN
        Note actualResult = jsonNoteParser.parse(FILE_NAME);
        //THEN
        verifyAll();
        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void testParseForADesignPatternNote() throws IOException {
        //GIVEN
        Note expectedResult = createDesignPatternsNote();
        expect(classLoaderSupplier.get()).andReturn(classLoader);
        expect(classLoader.getResourceAsStream(FILE_NAME)).andReturn(resourceAsStream);
        expect(objectMapper.readValue(resourceAsStream, DesignPatternNote.class)).andReturn(createDesignPatternsNote());
        replayAll();
        //WHEN
        Note actualResult = jsonNoteParser.parse(FILE_NAME, DesignPatternNote.class);
        //THEN
        verifyAll();
        assertThat(actualResult, is(expectedResult));
    }

    @Test(expectedExceptions = JsonNoteParseException.class, expectedExceptionsMessageRegExp = ERROR_MESSAGE)
    public void testParseForARegularNoteShouldThrowJsonNoteParseExceptionWhenReadingFileFails() throws IOException {
        //GIVEN
        expect(classLoaderSupplier.get()).andReturn(classLoader);
        expect(classLoader.getResourceAsStream(FILE_NAME)).andReturn(resourceAsStream);
        expect(objectMapper.readValue(resourceAsStream, RegularNote.class)).andThrow(new IOException(MESSAGE));
        replayAll();
        //WHEN
        jsonNoteParser.parse(FILE_NAME);
        //THEN
    }

    @Test(expectedExceptions = JsonNoteParseException.class)
    public void testParseForADesignPatterNoteShouldThrowJsonNoteParseExceptionWhenReadingFileFails() throws IOException {
        //GIVEN
        expect(classLoaderSupplier.get()).andReturn(classLoader);
        expect(classLoader.getResourceAsStream(FILE_NAME)).andReturn(resourceAsStream);
        expect(objectMapper.readValue(resourceAsStream, DesignPatternNote.class)).andThrow(new IOException(MESSAGE));
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
