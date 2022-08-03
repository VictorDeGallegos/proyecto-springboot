package com.proyecto.springboot.proyecto.caseuse;

import org.springframework.stereotype.Component;

import com.proyecto.springboot.proyecto.entity.User;
import com.proyecto.springboot.proyecto.service.UserService;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
