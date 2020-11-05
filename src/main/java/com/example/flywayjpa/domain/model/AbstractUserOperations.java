package com.example.flywayjpa.domain.model;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AbstractUserOperations {
    public ResponseEntity listUsers();
    public ResponseEntity addUser(User user);
}
