package com.senior.accelerator.notes_reader.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 * Service for Hello World.
 * @author Peter Takacs
 *
 */
@Service
public class HelloWorldService {

    private static final String MESSAGE = "message";
    private static final String MESSAGE_VALUE = "Hello World, Hello Master Peter!";


    /**
     * Populates the ModelMap with a Hello World message.
     * @param map - Modelmap
     */
    public void addMessage(ModelMap map) {
        map.put(MESSAGE, MESSAGE_VALUE);
    }

}
