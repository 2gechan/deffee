package com.gechan.fsrv.domain;

import jakarta.persistence.ElementCollection;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

// 구장 내에 개별 구장(A 풋살장의 1, 2, 3 등등 필드)
public class InField {
    private int fieldNum;

    @ElementCollection
    @Builder.Default
    private List<InFieldHoursOfUse> hoursOfUseList = new ArrayList<>();
}
