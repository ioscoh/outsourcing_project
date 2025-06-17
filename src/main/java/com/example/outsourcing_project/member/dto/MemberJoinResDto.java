package com.example.outsourcing_project.member.dto;
//MenberResDto : username, email, password >성공
//           status, message, memberId >실패
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

import lombok.Getter;

@Getter
public class MemberJoinResDto {
    //속
    private Long id;
    private int status;
    private String message;
    private String username;
    private String email;
    private String password;



    //생


    //기




}
