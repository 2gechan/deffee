package com.gechan.fsrv.domain.field;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class InFieldHoursOfUse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Field Entity pk

    private LocalTime startTime;
    private LocalTime endTime;
    private boolean useInField; // false 인 경우 사용 가능

    @ManyToOne
    @JoinColumn(name = "inField_id")
    private InField inField;

    protected InFieldHoursOfUse() {

    }

    public InFieldHoursOfUse(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.useInField = false;
    }

    public void useField() {
        this.useInField = true;
    }
}
