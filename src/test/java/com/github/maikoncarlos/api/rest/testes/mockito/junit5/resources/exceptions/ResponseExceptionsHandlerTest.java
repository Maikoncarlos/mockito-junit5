package com.github.maikoncarlos.api.rest.testes.mockito.junit5.resources.exceptions;

import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions.DataIntegrityViolationException;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions.UserNotfoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class ResponseExceptionsHandlerTest {

    public static final String EMAIL_JA_CADASTRADO = "Email já cadastrado";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado!";
    private static final String STATUS_400 = "400 BAD_REQUEST";
    @InjectMocks
    private ResponseExceptionsHandler exceptionsHandler;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void whenUserNotfoundExceptionThenReturnResponseEntity404() {
        ResponseEntity<StandardError> response = exceptionsHandler
                .userNotfound(new UserNotfoundException(OBJETO_NAO_ENCONTRADO),
                                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertNotEquals("/user/2", response.getBody().getPath());
    }

    @Test
    void whenDataIntegrityViolationThenReturnResponseEntity400() {
        ResponseEntity<StandardError> response = exceptionsHandler
                .dataIntegrityViolation(new DataIntegrityViolationException(EMAIL_JA_CADASTRADO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(EMAIL_JA_CADASTRADO, response.getBody().getError());
        assertEquals(STATUS_400, response.getBody().getStatus());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }
}
