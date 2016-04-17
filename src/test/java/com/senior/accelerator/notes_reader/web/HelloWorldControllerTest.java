package com.senior.accelerator.notes_reader.web;

import static org.easymock.EasyMock.expectLastCall;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.easymock.EasyMockSupport;
import org.springframework.ui.ModelMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.senior.accelerator.notes_reader.service.HelloWorldService;

/**
 * Test for {@HelloWorldController}.
 * @author Peter_Takacs
 */
public class HelloWorldControllerTest extends EasyMockSupport {

    private static final String INDEX = "index";
    private HelloWorldController helloWorldController;
    private HelloWorldService helloWorldService;

    @BeforeMethod
    public void init() {
        helloWorldController = new HelloWorldController();
        helloWorldService = createMock(HelloWorldService.class);
        helloWorldController.setHelloWorldService(helloWorldService);
        resetAll();
    }

    @Test
    public void testHelloWorld() {
        //GIVEN
        ModelMap map = new ModelMap();
        helloWorldService.addMessage(map);
        expectLastCall();
        replayAll();
        //WHEN
        String actual = helloWorldController.helloWorld(map);
        //THEN
        verifyAll();
        assertThat(INDEX, is(actual));
    }
}
