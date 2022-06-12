package com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.impl;

import com.github.maikoncarlos.api.rest.testes.mockito.junit5.domain.User;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.repositories.UserRepository;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.UserService;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions.UserNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> userOpt = repository.findById(id);
        return userOpt.orElseThrow(()-> new UserNotfoundException("Objeto User não encontrado"));
    }
}
