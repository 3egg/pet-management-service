package com.test.TestServer.exception;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException() {

    }

    public PetNotFoundException(String message) {
        super(message);
    }

}
