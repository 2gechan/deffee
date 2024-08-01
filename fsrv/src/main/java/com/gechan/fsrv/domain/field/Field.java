package com.gechan.fsrv.domain.field;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "field")
    @Builder.Default
    private List<FieldImage> fieldImageList = new ArrayList<>();

    protected Field() {

    }
    public Field(String fieldName, String ownerId) {
        this.fieldName = fieldName;
        this.ownerId = ownerId;
    }

    public void addInField () {
        inFieldList.add(new InField(inFieldList.size()));
    }

    public void addImage(FieldImage image) {
        fieldImageList.add(image);
    }

    public void clearImageList() {
        fieldImageList.clear();
    }

    public void clearInFieldList() {
        inFieldList.clear();
    }

}
