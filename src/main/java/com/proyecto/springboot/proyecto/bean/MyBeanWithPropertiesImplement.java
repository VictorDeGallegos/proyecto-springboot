package com.proyecto.springboot.proyecto.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties {

    private String nombre;
    private String apellido;

    public MyBeanWithPropertiesImplement(String name, String apellido) {
        this.nombre = name;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return nombre + "-" + apellido;
    }
}
