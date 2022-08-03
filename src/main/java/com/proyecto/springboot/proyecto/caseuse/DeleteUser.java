package com.proyecto.springboot.proyecto.caseuse;

import org.springframework.stereotype.Component;

import com.proyecto.springboot.proyecto.service.UserService;

@Component
public class DeleteUser {
    private UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void remove(Long id) {
        userService.delete(id);
    }
}
