package com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException{

    public DataIntegrityViolationException(String message){
        super(message);
    }
}
