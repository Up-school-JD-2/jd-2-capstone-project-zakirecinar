package io.upschool.exception;

public class FlightAlreadySavedException extends RuntimeException{
    public FlightAlreadySavedException(String message) {
        super(message);
    }

    public FlightAlreadySavedException(String message, Throwable cause) {
        super(message, cause);
    }
}
