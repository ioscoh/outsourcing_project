package com.example.outsourcing_project.member.service;

import com.example.outsourcing_project.global.config.PasswordEncoder;
import com.example.outsourcing_project.global.exception.MemberError;
import com.example.outsourcing_project.global.exception.MemberException;
import com.example.outsourcing_project.global.util.LoginJwtUtil;
import com.example.outsourcing_project.member.domain.entity.Member;
import com.example.outsourcing_project.member.dto.MemberJoinReqDto;
import com.example.outsourcing_project.member.dto.MemberJoinResDto;
import com.example.outsourcing_project.member.dto.MemberLoginReqDto;
import com.example.outsourcing_project.member.dto.MemberLoginResDto;
import com.example.outsourcing_project.member.repository.MemberRepository;
import com.example.outsourcing_project.task.domain.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.regex.Pattern;

@Service
public class MemberService {
    //속
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginJwtUtil loginJwtUtil;



    private static final Pattern EMAIL_REGEX =
            Pattern.compile(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
            );

    private static final Pattern PASSWORD_REGEX =
            Pattern.compile(
                    "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-\\=\\[\\]{};:'\"\\\\|,.<>\\/?]).{8,}$"
            );


    //생
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, LoginJwtUtil loginJwtUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginJwtUtil = loginJwtUtil;

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
     * 1.- 중복된 `이메일`로 가입하면 입셉션 애러를 발생 시킵니다.
     *   - `이메일`과 `비밀번호` 형식이 올바르지 않은 경우
     *      배드 리퉤스트 에러를 발생 시깁니다.
     *      1-1 이메일과 비밀번호를 불러옵니다.
     *      1-2 이메일이 중복된 데이터인지 확인하는 로직을 작성하기
     *      1-3 중복이라면 입셉션 에러를 발생시키는 로직 작성하기
     *      1-4 이메일이 중복이 아니라면 형식이 올바른지 확인하는 로직을 작성하기
     *      1-5 비밀번호가 올바른 형식인지 확인 하는 로직을 작성하기.--> 그 후 엔코드 메서드 사용
     *      1-6 이메일과 비밀번호의 형식이 올바르면 회원가입을 성골 하는 로직을 작성하기
     * 2.이메일과 비밀번호를 올바르게 입력된 값을 레퍼지토리에 저장합니다.
     */
public MemberJoinResDto memberJoinService(MemberJoinReqDto memberJoinReqDto) {
    // 1-1 이메일과 비밀번호를 불러옵니다.
    String joinReqDtoUsername = memberJoinReqDto.getUsername();
    String joinResDtoEmail = memberJoinReqDto.getEmail();
    String joinResDtoPassword = memberJoinReqDto.getPassword(); //???encode???

    // 1-2 이메일이 중복된 데이터인지 확인하는 로직을 작성하기
    if (memberRepository.existsByEmail(joinResDtoEmail)) {
        //1-3 중복이라면 입셉션 에러를 발생시키는 로직 작성하기
        throw new MemberException(MemberError.EMAIL_THAT_ALREADY_EXISTS);
    }
    //1-4 이메일이 중복이 아니라면 형식이 올바른지 확인하는 로직을 작성하기
    if (!EMAIL_REGEX.matcher(joinResDtoEmail).matches()) {
        throw new MemberException(MemberError.INVALID_EMAIL_FORMAT);
    }
    //  1-5 비밀번호가 올바른 형식인지 확인 하는 로직을 작성하기.--> 엔코드 전 형식 검사 먼저
    if (!PASSWORD_REGEX.matcher(joinResDtoPassword).matches()) {
        throw new MemberException(MemberError.INVALID_PASSWORD_FORMAT);
    }
    //이제 엔코드 로직 ->암호화 과정입니다.
    String encode = passwordEncoder.encode(joinResDtoPassword);

    // 1-6 이메일과 비밀번호의 형식이 올바르면 회원가입을 성골 하는 로직 작성
    Member member = Member.builder()
            .username(joinReqDtoUsername)
            .email(joinResDtoEmail)
            .password(encode)
            .isDeleted(false)
            .role(Member.Role.USER)
            .build();

    // 2.이메일과 비밀번호를 올바르게 입력된 값을 레퍼지토리에 저장합니다.
    memberRepository.save(member);
   //성공시 아래 로직 반환됩니다.

    return new MemberJoinResDto(Task.Status.DONE, "회원가입 완료되었습니다.", member.getId());
}


    public MemberLoginResDto memberloginService(MemberLoginReqDto request) {

        String email = request.getEmail();
        String password = request.getPassword();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(
                () -> new MemberException(MemberError.USER_NOT_FOUND));


        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new MemberException(MemberError.INVALID_PASSWORD);
        }

        String token = LoginJwtUtil.generateToken(
                member.getId(), member.getRole()
        );
        //토큰 발급
        return new MemberLoginResDto(
                HttpStatus.OK.value(), "로그인 완료되었습니다.", token, member.getId()
        );

    }




}
