package com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions;

public class UserNotfoundException extends RuntimeException{

    public UserNotfoundException(String message){
        super(message);
    }
}
