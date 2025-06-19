package com.example.outsourcing_project.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginReqDto {
    private String userName;
    private String password;
    private String email;

}
