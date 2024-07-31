package com.gechan.fsrv.domain.field;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FieldImage {

    @Id
    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;

    private String fileName;
    private int ord;
}
