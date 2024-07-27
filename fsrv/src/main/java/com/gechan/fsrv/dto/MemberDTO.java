package com.gechan.fsrv.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    String id;
    String password;
    String name;
    String phone;
    String team;
    LocalDate signDate;

}
