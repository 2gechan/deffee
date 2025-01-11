package com.gechan.devlog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Map;

@RestController
public class HomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public Map<String, String> home() {

        return Map.of("message", "서버 연결 ok");
 
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of("RESULT", "OK");
    }
}
