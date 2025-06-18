package com.example.outsourcing_project.member.dto;
//MenberResDto : username, email, password >성공
//           status, message, id >실패
//{
//		"status": 200
//		"message": "회원가입 완료되었습니다."
//		"id": 1
//}
//{
//    "username": "lee",
//		"email": "sparta@xxxx.com",
//		"password": "!Password12345"
//}

import com.example.outsourcing_project.global.exception.MemberError;
import lombok.Getter;

@Getter
public class MemberJoinResDto {

    //속
    private final MemberError memberError;
    private final String message;





    //생
    public MemberJoinResDto(MemberError memberError, String message) {
        this.memberError = memberError;
        this.message = message;
    }

    //기




}
