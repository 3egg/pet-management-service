package com.test.TestServer.exceptions;

public class PetNotFoundException extends RuntimeException{

    public PetNotFoundException(String message) {
        super(message);
    }
}
