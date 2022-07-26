package com.proyecto.springboot.proyecto.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency {

  @Override
  public void saludar() {
    System.out.println("Hola desde un componente");

  }

}
