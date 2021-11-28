package com.peopleflow.producer.exception;

public class EmployeeException extends RuntimeException {

    public EmployeeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
