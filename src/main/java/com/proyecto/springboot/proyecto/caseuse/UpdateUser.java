package com.proyecto.springboot.proyecto.caseuse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.proyecto.springboot.proyecto.entity.User;
import com.proyecto.springboot.proyecto.service.UserService;

@Component
public class UpdateUser {
    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }

    public User update(User newUser, Long id) {
        return userService.update(newUser, id);
    }
}
