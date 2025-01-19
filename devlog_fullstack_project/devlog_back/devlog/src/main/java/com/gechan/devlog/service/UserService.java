package com.gechan.devlog.service;

import com.gechan.devlog.dto.MemberDTO;
import com.gechan.devlog.dto.UserCreateRequestDTO;
import com.gechan.devlog.dto.UserLoginRequestDTO;

public interface UserService {

    Long join(UserCreateRequestDTO userCreateRequestDTO);

    MemberDTO login(UserLoginRequestDTO userLoginRequestDTO);
}
