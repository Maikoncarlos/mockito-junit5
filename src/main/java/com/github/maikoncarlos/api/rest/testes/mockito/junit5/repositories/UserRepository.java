package com.github.maikoncarlos.api.rest.testes.mockito.junit5.repositories;

import com.github.maikoncarlos.api.rest.testes.mockito.junit5.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
