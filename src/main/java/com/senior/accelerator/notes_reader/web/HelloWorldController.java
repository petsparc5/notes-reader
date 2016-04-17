package com.senior.accelerator.notes_reader.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.senior.accelerator.notes_reader.service.HelloWorldService;

/**
 * Controller for Hello World.
 * @author Peter Takacs
 *
 */
@Controller
public class HelloWorldController {

    private static final String LOG_MESSAGE = "Alert! Someone is trying to make contact!";
    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private HelloWorldService helloWorldService;

    /**
     * Display Hello World.
     * @param map - Modelmap
     * @return with index.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloWorld(ModelMap map) {
        logger.error(LOG_MESSAGE);
        helloWorldService.addMessage(map);
        return "index";
    }

    protected void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

}
