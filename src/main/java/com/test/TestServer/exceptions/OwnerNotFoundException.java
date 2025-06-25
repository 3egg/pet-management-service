package com.test.TestServer.exceptions;

public class OwnerNotFoundException extends RuntimeException{

    public OwnerNotFoundException(String message) {
        super(message);
    }
}
