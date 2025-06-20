package com.example.outsourcing_project.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginError {
    private final int    status;   //
    private final String message;  // 오류 메시지
}
