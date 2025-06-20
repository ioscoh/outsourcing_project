package com.example.outsourcing_project.global.exception;

public class TaskUpdateNotFoundException extends RuntimeException {
    public TaskUpdateNotFoundException(String message) {
        super(message);
    }
}
