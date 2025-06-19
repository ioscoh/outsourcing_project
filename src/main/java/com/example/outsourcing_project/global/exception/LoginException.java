package com.example.outsourcing_project.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoginException {
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<LoginError> handleMember(MemberException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;   // 상황에 맞게 바꿔도 OK
        LoginError body = new LoginError(status.value(), ex.getMessage());
        return new ResponseEntity<>(body, status);
    }

}
