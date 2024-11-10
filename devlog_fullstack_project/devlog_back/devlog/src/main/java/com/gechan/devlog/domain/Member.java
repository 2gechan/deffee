package com.gechan.devlog.domain;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;

    private String pw;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Post> postList = new ArrayList<>();
}
