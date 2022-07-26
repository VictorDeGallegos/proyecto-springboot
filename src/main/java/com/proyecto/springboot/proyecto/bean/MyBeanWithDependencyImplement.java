package com.proyecto.springboot.proyecto.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

  private MyOperation myOperation;

  public MyBeanWithDependencyImplement(MyOperation myOperation) {
    this.myOperation = myOperation;
  }

  @Override
  public void printWithDependency() {
    int number = 1;
    System.out.println("El resultado de la suma es: " + myOperation.sum(number));
    System.out.println("Hola desde la implementacion de un bean con dependencia");
  }
}
