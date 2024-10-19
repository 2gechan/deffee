package com.gechan.devlog.domain;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;

@Entity
@Table
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
