package com.proyecto.springboot.proyecto.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency {

  Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
  private MyOperation myOperation;

  public MyBeanWithDependencyImplement(MyOperation myOperation) {
    this.myOperation = myOperation;
  }

  @Override
  public void printWithDependency() {
    LOGGER.info("Hemos ingresado al metodo printWithDependency");
    int number = 1;
    LOGGER.debug("Hemos ingresado el numero: " + number);
    System.out.println("El resultado de la suma es: " + myOperation.sum(number));
    System.out.println("Hola desde la implementacion de un bean con dependencia");
  }
}
