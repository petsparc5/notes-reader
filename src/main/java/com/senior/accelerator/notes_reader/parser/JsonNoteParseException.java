package com.senior.accelerator.notes_reader.parser;

/**
 * Exception that is thrown when parsing of a note is unsuccessful.
 * @author Peter_Takacs
 */
public class JsonNoteParseException extends RuntimeException {

    public JsonNoteParseException() {
        super();
    }

    public JsonNoteParseException(String message) {
        super(message);
    }

    public JsonNoteParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonNoteParseException(Throwable cause) {
        super(cause);
    }
}
