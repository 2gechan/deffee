package com.gechan.devlog.controller;

import com.gechan.devlog.dto.MemberDTO;
import com.gechan.devlog.dto.UserCreateRequestDTO;
import com.gechan.devlog.dto.UserLoginRequestDTO;
import com.gechan.devlog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/join")
    public Map<String, Long> join(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        Long memberId = userService.join(userCreateRequestDTO);

        return Map.of("새 회원", memberId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Map<String, MemberDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        MemberDTO loginMember = userService.login(userLoginRequestDTO);

        return Map.of("login Member", loginMember);
    }
}
