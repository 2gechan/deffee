package com.gechan.devlog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public void home() {
 
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of("RESULT", "OK");
    }
}
