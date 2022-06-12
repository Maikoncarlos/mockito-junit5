package com.github.maikoncarlos.api.rest.testes.mockito.junit5.services;

import com.github.maikoncarlos.api.rest.testes.mockito.junit5.domain.User;

public interface UserService {

    User findById(Integer id);
}
