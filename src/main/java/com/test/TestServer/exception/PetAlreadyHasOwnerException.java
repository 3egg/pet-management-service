package com.test.TestServer.exception;

public class PetAlreadyHasOwnerException extends RuntimeException {

    public PetAlreadyHasOwnerException() {

    }

    public PetAlreadyHasOwnerException(String message) {
        super(message);
    }

}
