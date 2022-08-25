package com.example.Server.exception;

public class ServerNotFoundException extends RuntimeException {

    public ServerNotFoundException(String message) {
        super(message);
    }
}
