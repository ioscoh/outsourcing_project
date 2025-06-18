package com.example.outsourcing_project.member.controller;

import com.example.outsourcing_project.global.exception.MemberError;
import com.example.outsourcing_project.global.exception.MemberException;
import com.example.outsourcing_project.member.domain.entity.Member;
import com.example.outsourcing_project.member.dto.MemberJoinReqDto;
import com.example.outsourcing_project.member.dto.MemberJoinResDto;
import com.example.outsourcing_project.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/members")
public class MemberController {
    //속
    private final MemberService memberService;



    //생
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



    //기
    /**
     * 회원가입을 하기 위한 컨트롤러 메서드 입니다.
     */
    /**
     * 1.서비스에서 뜨로우 한 입셉션 받하서 실페를 알리는 로직 쓰기
     * 2.서비스에서 정상 값을 저장한걸 불러와서 성공을 알리는 로직 쓰기
     */
    //서비스에서 정상 값을 저장한걸 불러와서 성공을 알리는 로직 쓰기
    public  ResponseEntity<String> memberJoinController(@RequestBody MemberJoinReqDto memberJoinReqDto) {
        memberService.memberJoinService(memberJoinReqDto);
        return ResponseEntity.ok()
                .body("회원가입 완료되었습니다.");

    }
    // 서비스에서 뜨로우 한 입셉션 받하서 실페를 알리는 로직 쓰기
    @ExceptionHandler(MemberException.class)
    public MemberJoinResDto memberJoinExceptionController(MemberException memberException) {
        MemberJoinResDto memberJoinResDto = new MemberJoinResDto(
                memberException.getMemberError(),
                memberException.getMessage("가입정보가 잘못되었습니다.")
        );
        return memberJoinResDto;

    }



}
