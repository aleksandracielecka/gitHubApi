package com.example.github.exception;

public class CommitNotFoundException extends RuntimeException{

    public CommitNotFoundException(String message){
        super(message);
    }

    public CommitNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
