package com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.impl;

import com.github.maikoncarlos.api.rest.testes.mockito.junit5.domain.User;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.domain.dto.UserDTO;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.repositories.UserRepository;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions.DataIntegrityViolationException;
import com.github.maikoncarlos.api.rest.testes.mockito.junit5.services.exceptions.UserNotfoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID       = 1;
    public static final String NAME      = "Maikon";
    public static final String EMAIL     = "maikon@gmail.com";
    public static final String PASSWORD  = "123";
    public static final int INDEX        = 0;

    @InjectMocks
    private UserServiceImpl serviceImpl;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> userOpt;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUsers();
    }

    @Test
    @DisplayName("Quando chamar o metodo findById retorne um Usuario")
    void whenFindByIdThenReturnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(userOpt);

        User response = serviceImpl.findById(ID);

        assertNotNull(response, "não pode ser nulo");
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    @DisplayName("Quando chamar o metodo findById retorna excessao ObjectNotFound")
    void whenFindByIdThenReturnObjectNotFound(){
        when(repository.findById(anyInt())).thenThrow( new UserNotfoundException("Objeto não encontrado!"));

        try {
            serviceImpl.findById(ID);
        }catch (Exception ex){
            assertEquals(UserNotfoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado!", ex.getMessage());
        }

    }

    @Test
    @DisplayName("Quando chamar metodo findAll retorna Lista de Usuarios")
    void whenFindAllThenReturnAnListOfUser() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = serviceImpl.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class , response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    @DisplayName("Quando chamar o metodo create retorne um Usuario")
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = serviceImpl.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
    }

    @Test
    @DisplayName("Quando chamar o metodo create retorne uma excessao DataIntegrityViolationException")
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(userOpt);

        try{
            userOpt.get().setId(2);
            serviceImpl.create(userDTO);
        }catch(Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado!", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Quando chamar o metodo update retorne uma excessao DataIntegrityViolationException")
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(userOpt);

        try{
            userOpt.get().setId(2);
            serviceImpl.create(userDTO);
        }catch(Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado!", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Quando chamar o metodo update retorne um Usuario")
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = serviceImpl.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
    }


    @Test
    void delete() {
    }

    private void startUsers(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        userOpt = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}