package com.gechan.devlog.service;

import com.gechan.devlog.dto.MemberDTO;
import com.gechan.devlog.dto.UserCreateRequestDTO;

public interface UserService {

    Long join(UserCreateRequestDTO userCreateRequestDTO);
}
