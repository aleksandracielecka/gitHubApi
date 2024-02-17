package com.example.github.exception;

public class RepositoryNotFoundException extends RuntimeException{

    public RepositoryNotFoundException(String message){
        super(message);
    }

    public RepositoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
