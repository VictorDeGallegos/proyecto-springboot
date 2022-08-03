package com.proyecto.springboot.proyecto.caseuse;

import java.util.List;

import com.proyecto.springboot.proyecto.entity.User;
import com.proyecto.springboot.proyecto.service.UserService;

public class GetUserImplement implements GetUser {

    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
