package com.gechan.fsrv.service;

import com.gechan.fsrv.dto.MemberDTO;

public interface MemberService {
    void join(MemberDTO memberDTO);

    void login(String id, String password);

    void findById(String id);
}
