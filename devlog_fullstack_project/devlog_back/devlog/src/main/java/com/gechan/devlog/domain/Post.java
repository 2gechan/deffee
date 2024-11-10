package com.gechan.devlog.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String description;
    private Date createDate;
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member author;
}
