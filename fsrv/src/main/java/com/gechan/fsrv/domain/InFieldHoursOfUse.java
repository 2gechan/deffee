package com.gechan.fsrv.domain;

// 풋살장의 각 구장별 사용 가능 시간대 목록
public class InFieldHoursOfUse {
    private Long fno; // Field Entity pk
    private int InfieldNum; // fno Field의 속해 있는 각 fieldNum
    private int startTime;
    private int endTime;
    private boolean useInField;
}
