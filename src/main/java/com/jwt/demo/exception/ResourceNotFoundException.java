package com.jwt.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Integer id) {
        super(String.format("%s not found with id: %s", resource , id));
    }
}
