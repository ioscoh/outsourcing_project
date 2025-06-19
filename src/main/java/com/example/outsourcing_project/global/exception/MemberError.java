package com.example.outsourcing_project.global.exception;

import lombok.Getter;

@Getter
public enum MemberError {

    EMAIL_THAT_ALREADY_EXISTS("이미 존재하는 이메일입니다."),
    INVALID_EMAIL_FORMAT("잘못된 이메일 형식입니다."),
    INVALID_PASSWORD_FORMAT("잘못된 비밀번호 형식입니다."),
    USER_NOT_FOUND("등록된 사용자가 없습니다."),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),
    INVALID_JWT("유효하지 않는 서명 입니다"),
    NO_PERMISSION("정상적인 접근이 아닙니다. 본인 의 정보만 처리 가능합니다.");

    private final String message;

    MemberError(String message) {
        this.message = message;
    }


}
