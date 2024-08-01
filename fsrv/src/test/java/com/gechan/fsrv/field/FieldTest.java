package com.gechan.fsrv.field;

import com.gechan.fsrv.domain.field.Field;
import com.gechan.fsrv.domain.member.Member;
import com.gechan.fsrv.dto.MemberRole;
import com.gechan.fsrv.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@SpringBootTest
public class FieldTest {

    @Autowired MemberService memberService;

    @DisplayName("필드 도메인 생성 테스트")
    @Test
    void createFieldTest() {
        /*
        1. Field 생성, Field 이미지 저장
        2. Field 내에 구장 생성(InField), 구장 개수는 사용자 선택
        3. 각 구장 별 이용 가능 시간 생성(InFieldHoursOfUse)
         */
        Field field = new Field("우리 풋살장", "");
    }
}
