package com.gechan.fsrv.controller.member;

import com.gechan.fsrv.dto.MemberDTO;
import com.gechan.fsrv.repository.MemberRepository;
import com.gechan.fsrv.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public Map<String, String> join(@RequestBody MemberDTO memberDTO)  {

        memberService.join(memberDTO);

        return Map.of("RESULT", memberDTO.getId());
    }
}
