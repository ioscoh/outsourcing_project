package com.example.outsourcing_project.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginResDto {
    private final int    status;      // 200
    private final String message;     // "로그인 완료되었습니다."
    private final String JWTToken;    // 토큰
    private final Long id; // 토큰 발급 시 아이디 값을 알려주기 위해 추가



}
