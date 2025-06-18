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
import com.example.outsourcing_project.task.domain.entity.Task;
import lombok.Getter;

@Getter
public class MemberJoinResDto {

    //속
    private final Task.Status status;
    private final String message;
    private final Long id;





    //생
    public MemberJoinResDto(Task.Status status, String message, Long id) {
        this.status = status;
        this.message = message;
        this.id = id;
    }

    //기




}
