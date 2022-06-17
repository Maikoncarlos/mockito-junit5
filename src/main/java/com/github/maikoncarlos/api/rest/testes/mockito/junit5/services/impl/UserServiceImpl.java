package com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.impl;

import com.github.maikoncarlos.api.rest.testes.mockito.junit5.domain.User;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.domain.dto.UserDTO;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.repositories.UserRepository;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.UserService;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions.DataIntegrityViolationException;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions.UserNotfoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> userOpt = repository.findById(id);
        return userOpt.orElseThrow(()-> new UserNotfoundException("Objeto User não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    private void findByEmail(UserDTO userDTO){
        Optional<User> userOptional = repository.findByEmail(userDTO.getEmail());
        if(userOptional.isPresent() && !userOptional.get().getId().equals(userDTO.getId())){
            throw new DataIntegrityViolationException("Email já cadastrado!");
        }
    }
}
