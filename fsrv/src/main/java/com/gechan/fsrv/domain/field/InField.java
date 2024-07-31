package com.gechan.fsrv.domain.field;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalTime;
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

    // 사용자가 해당 필드의 원하는 운영 가능 시간대 추가
    public void addHoursOfUse(LocalTime sTime, LocalTime eTime) {
        hoursOfUseList.add(new InFieldHoursOfUse(sTime, eTime));
    }

    public InField(int fieldCount) {
        this.fieldNum = fieldCount + 1;
    }

    protected InField() {
    }
}
