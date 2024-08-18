package com.gechan.openmarket.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
import java.util.stream.Collectors;

// Security User 객체 상속
public class MemberDTO extends User {

    private String id, pw, nickname;

    private List<String> roleNames = new ArrayList<>();

    public MemberDTO(String id, String pw, String nickname, List<String> roleNames) {
        super(id, pw, roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.roleNames = roleNames;
    }

    public Map<String, Object> getClaims() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("pw", pw);
        map.put("nickname", nickname);
        map.put("roleNames", roleNames);

        return map;
    }
}
