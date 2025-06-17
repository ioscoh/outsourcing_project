package com.example.outsourcing_project.member.service;

import com.example.outsourcing_project.member.dto.MemberJoinResDto;
import com.example.outsourcing_project.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl {
    //속
    private final MemberRepository memberRepository;


    //생
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    //기
    /**
     * 회원가입을 하기 위한 서비스 메서드 입니다.
     */
    //아이디 이메일 형식이고 비민번로는 퀴크립드로 인코딩하여 씁니다.
    //대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함합니다.
    //비밀번호는 최소 8글자 이상이어야 합니다.
    //회원가입시 일반 사용자(USER) 또는 관리자(ADMIN)로 가입할 수 있다.
    //예외처리
    //- 중복된 `이메일`로 가입하는 경우
    //- `이메일`과 `비밀번호` 형식이 올바르지 않은 경우
    /**
     * 1.- 중복된 `이메일`로 가입하면 낫 파운드 애러를 발생 시킵니다.
     *   - `이메일`과 `비밀번호` 형식이 올바르지 않은 경우
     *      배드 리퉤스트 에러를 발생 시깁니다.
     *      1-1 이메일과 비밀번호를 불러옵니다.
     *      1-2 이메일이 중복된 데이터인지 확인하는 로직을 작성하기
     *      1-3 중복이라면 낫 파운드 에러 를 발생시키는 로직 작성하기
     *      1-4 이메일이 중복이 아니라면 형식이 올바른지 확인하는 로직을 작성하기
     *      1-5 비밀번호가 올바른 형식인지 확인 하는 로직을 작성하기.
     *      1-6 이메일과 비밀번호의 형식이 올바르면 회원가입을 성골 하는 로직을,
     *          틀리면 베드 리퀘스트를 발생시키는 로직을 작성하기
     * 2.이메일과 비밀번호를 올바르게 입력된 값을 겟 이메일 후 레퍼지 토리에 저장합니다.
     */
public void memberJoinService(MemberJoinResDto memberJoinResDto) {
    // 1-1 이메일과 비밀번호를 불러옵니다.
    String joinResDtoEmail = memberJoinResDto.getEmail();
    String joinResDtoPassword = memberJoinResDto.getPassword(); //???encode???




}


}
