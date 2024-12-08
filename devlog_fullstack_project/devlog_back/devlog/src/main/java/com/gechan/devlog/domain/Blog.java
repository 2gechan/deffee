package com.gechan.devlog.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Blog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;

    private String blogName;

    @OneToOne(mappedBy = "myBlog", fetch = FetchType.LAZY)
    private Member owner;

    @OneToMany(mappedBy = "blog")
    private List<Post> postList = new ArrayList<>();

    public Member getOwner() {
        return owner;
    }
}
