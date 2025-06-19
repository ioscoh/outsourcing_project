package com.example.outsourcing_project.member.controller;

import com.example.outsourcing_project.member.domain.entity.Member;
import com.example.outsourcing_project.member.dto.*;
import com.example.outsourcing_project.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@ControllerAdvice
@RequestMapping("/api/members")
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
    @PostMapping("/signup")
    public  ResponseEntity<MemberJoinResDto> memberJoinController(
            @RequestBody MemberJoinReqDto memberJoinReqDto
    ) {

        MemberJoinResDto memberJoinResDto = memberService.memberJoinService(memberJoinReqDto);
         return new ResponseEntity<>(memberJoinResDto, HttpStatus.OK);

    }


    @GetMapping("/login")
    public ResponseEntity<MemberLoginResDto> memberloginController(
            @RequestBody MemberLoginReqDto memberLoginReqDto
    ) {
        MemberLoginResDto memberLoginResDto = memberService.memberloginService(memberLoginReqDto);


        return new  ResponseEntity<>(memberLoginResDto, HttpStatus.OK);
    }

    //회원 탈퇴(삭제) api
    @DeleteMapping("/{id}")
    public ResponseEntity<Member.MemberLeaveDto> memberLeaveController(
            @PathVariable Long id,
            @RequestHeader("Authorization") String bearerHeader
    ) {
        Member.MemberLeaveDto memberLeaveDto = memberService.memberLeaveService(id, bearerHeader);
        return ResponseEntity.ok(memberLeaveDto);

    }




}
