package com.gechan.fsrv.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class InField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int fieldNum;

    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "inField")
    @Builder.Default
    private List<InFieldHoursOfUse> hoursOfUseList = new ArrayList<>();
}
