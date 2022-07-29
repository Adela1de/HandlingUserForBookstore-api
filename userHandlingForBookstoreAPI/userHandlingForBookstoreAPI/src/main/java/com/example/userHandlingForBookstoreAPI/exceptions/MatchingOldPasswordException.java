package com.example.userHandlingForBookstoreAPI.exceptions;

public class MatchingOldPasswordException extends RuntimeException{
    public MatchingOldPasswordException(String message) { super(message); }

    public MatchingOldPasswordException(String message, Throwable cause) { super(message, cause); }
}
