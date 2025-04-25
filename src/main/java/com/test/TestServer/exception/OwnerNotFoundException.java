package com.test.TestServer.exception;

public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException() {

    }

    public OwnerNotFoundException(String message) {
        super(message);
    }

}
