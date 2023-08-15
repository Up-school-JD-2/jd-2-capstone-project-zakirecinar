package io.upschool.exception;

public class CompanyAlreadySavedException extends RuntimeException{
    public CompanyAlreadySavedException(String message){
        super(message);
    }

    public CompanyAlreadySavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
