package com.senior.accelerator.notes_reader.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertTrue;

import org.springframework.ui.ModelMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for {@HelloWorldService}.
 * @author Peter_Takacs
 */
public class HelloWorldServiceTest {

    private static final String MESSAGE = "message";
    private static final String MESSAGE_VALUE = "Hello World, Hello Master Peter!";

    private HelloWorldService helloWorldService;

    @BeforeMethod
    public void init() {
        helloWorldService = new HelloWorldService();
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

}
