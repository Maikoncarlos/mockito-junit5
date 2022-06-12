package com.github.maikoncarlos.api.rest.testes.mockito.junit5.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    @Email
    private String email;

    private String password;
}
