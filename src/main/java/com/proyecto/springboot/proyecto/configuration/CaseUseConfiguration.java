package com.proyecto.springboot.proyecto.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proyecto.springboot.proyecto.caseuse.GetUser;
import com.proyecto.springboot.proyecto.caseuse.GetUserImplement;
import com.proyecto.springboot.proyecto.service.UserService;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService) {
        return new GetUserImplement(userService);
    }
}
