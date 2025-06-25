package com.test.TestServer.exceptions;

public class PetHasOwnerException extends RuntimeException{

    public PetHasOwnerException(String message) {
        super(message);
    }
}
