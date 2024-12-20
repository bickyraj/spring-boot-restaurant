package com.bickyraj.demo.controller;

import com.bickyraj.demo.dto.Greeting;
import com.bickyraj.demo.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) {
        System.out.println(message);
        return new Greeting(HtmlUtils.htmlEscape(message.getMessage()));
    }
}
