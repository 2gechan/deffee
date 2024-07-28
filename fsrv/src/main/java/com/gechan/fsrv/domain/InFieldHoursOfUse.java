package com.gechan.fsrv.domain;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class InFieldHoursOfUse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Field Entity pk

    private LocalTime startTime;
    private LocalTime endTime;
    private boolean useInField;

    @ManyToOne
    @JoinColumn(name = "inField_id")
    private InField inField;
}
