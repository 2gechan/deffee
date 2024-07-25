package com.gechan.fsrv.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    MemberRole memberType;
    String id;
    String password;
    String name;
    String phone;
    String team;
    Date signDate;


    boolean use;

}
