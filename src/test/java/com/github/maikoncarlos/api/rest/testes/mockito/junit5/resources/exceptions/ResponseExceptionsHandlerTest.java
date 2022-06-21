package com.github.maikoncarlos.api.rest.testes.mockito.junit5.resources.exceptions;

import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions.UserNotfoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResponseExceptionsHandlerTest {

    @InjectMocks
    private ResponseExceptionsHandler exceptionsHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenUserNotfoundExceptionThenReturnResponseEntity() {
        ResponseEntity<StandardError> response = exceptionsHandler
                .userNotfound(new UserNotfoundException("Objeto não encontrado!"),
                                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Objeto não encontrado!", response.getBody().getError());
    }

    @Test
    void dataIntegrityViolation() {
    }
}