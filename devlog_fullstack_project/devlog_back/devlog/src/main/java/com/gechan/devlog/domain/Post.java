package com.gechan.devlog.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String description;
    private Date createDate;
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

}
