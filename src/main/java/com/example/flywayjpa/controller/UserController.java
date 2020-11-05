package com.example.flywayjpa.controller;

import com.example.flywayjpa.domain.model.AbstractUserOperations;
import com.example.flywayjpa.domain.model.User;
import com.example.flywayjpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements AbstractUserOperations {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users", produces = "application/json")
    @Override
    public ResponseEntity listUsers() {
        return userService.listUsers();
    }

    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    @Override
    public ResponseEntity addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
