package com.example.outsourcing_project.global.exception;


//멤버에러의 문자들을 꺼내주는 틀래스 입니다.
public class MemberException extends RuntimeException {
  //속
  private final MemberError memberError;


  //생
  public MemberException(MemberError memberError) {
    super(memberError.name());
    this.memberError = memberError;
  }


  //기
  public MemberError getMemberError() {
    return memberError;
  }


  public String getMessage(String s) {
    return getMessage();
  }
}
