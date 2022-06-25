package com.github.maikoncarlos.api.rest.testes.mockito.junit5.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandardError {

    private LocalDateTime timestamp;
    private String status;
    private String error;
    private String path;
}
