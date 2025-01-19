package com.gechan.devlog.domain;

import com.gechan.devlog.dto.MemberDTO;
import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;

    private String pw;

    private String name;

    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog myBlog;

    protected Member() {
    }

    public Member(String email, String pw, String name, String phone) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Blog getMyBlog() {
        return myBlog;
    }
}
