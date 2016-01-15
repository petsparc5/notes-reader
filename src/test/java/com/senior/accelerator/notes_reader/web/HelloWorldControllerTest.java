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
 * Created by Peter_Takacs on 14/01/2016.
 */
public class HelloWorldControllerTest extends EasyMockSupport {

    public static final String INDEX = "index";
    public static final String PLAY = "play";
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

    @Test
    public void testPlay() {
        //GIVEN
        ModelMap map = new ModelMap();
        helloWorldService.playWithDB(map);
        expectLastCall();
        replayAll();
        //WHEN
        String actual = helloWorldController.play(map);
        //THEN
        verifyAll();
        assertThat(PLAY, is(actual));
    }
}
