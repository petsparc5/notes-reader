package com.senior.accelerator.notes_reader.parser;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senior.accelerator.notes_reader.dao.Note;
import com.senior.accelerator.notes_reader.dao.RegularNote;

/**
 * Class responsible for parsing @(Note) from a Json file.
 * @author Peter_Takacs
 */
@Component
public class JsonNoteParser {

    private static final String ERROR_MESSAGE = "Parsing error occurred with file: %s, message: %s";
    private static final String LOG_MESSAGE = "Successfuly parsed: %s";
    private final static Logger logger = LoggerFactory.getLogger(JsonNoteParser.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parses a file into a @RegularNote.
     * @param fileName - name of the file that will be passed into a note.
     * @return RegularNote parsed from the file.
     */
    public Note parse(String fileName) {
        return parse(fileName, RegularNote.class);
    }

    /**
     * Parses a file into a specific Note.
     * @param fileName - name of the file that will be passed into a note.
     * @param noteType - type of note that needs to be parsed.
     * @return Note for type: noteType parsed from the file.
     */
    public Note parse(String fileName, Class<? extends Note> noteType) {
        File file = new File(fileName);
        Note parsedNote = null;
        try {
            parsedNote = objectMapper.readValue(file, noteType);
            logger.debug(String.format(LOG_MESSAGE, fileName));
        } catch (IOException e) {
            String errorMessage = String.format(ERROR_MESSAGE, fileName, e.getMessage());
            throw new JsonNoteParseException(errorMessage, e);
        }
        return parsedNote;
    }

    protected void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
