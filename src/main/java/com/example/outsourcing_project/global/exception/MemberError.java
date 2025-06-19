package com.example.outsourcing_project.global.exception;

import lombok.Getter;

@Getter
public enum MemberError {

    EMAIL_THAT_ALREADY_EXISTS("이미 존재하는 이메일입니다."),
    INVALID_EMAIL_FORMAT("잘못된 이메일 형식입니다."),
    INVALID_PASSWORD_FORMAT("잘못된 비밀번호 형식입니다."),
    USER_NOT_FOUND("등록된 사용자가 없습니다."),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다.");

    private final String message;

    MemberError(String message) {
        this.message = message;
    }


}
