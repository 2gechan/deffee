package com.gechan.devlog.controller;

import com.gechan.devlog.dto.RequestUserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(method = RequestMethod.POST, value = "/join")
    public void join() {

    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public void login(RequestUserDTO requestUserDTO) {

    }
}
