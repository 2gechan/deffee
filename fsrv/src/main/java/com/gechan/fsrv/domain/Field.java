package com.gechan.fsrv.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno; // field pk
    private String fieldName; // 회원들에게 보여질 구장 이름
    private String ownerId; // 구장 소유자 회원 ID

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "field")
    @Builder.Default
    private List<InField> inFieldList = new ArrayList<>();

}
