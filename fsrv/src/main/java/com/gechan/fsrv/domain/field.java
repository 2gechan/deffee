package com.gechan.fsrv.domain;

import jakarta.persistence.ElementCollection;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class field {

    private Long fno; // field pk
    private String fieldName; // 회원들에게 보여질 구장 이름
    private String id; // 구장 소유자 회원 ID
    @ElementCollection
    @Builder.Default
    private List<InField> InFieldList = new ArrayList<>();

}
