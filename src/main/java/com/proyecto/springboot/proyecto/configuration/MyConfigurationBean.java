package com.proyecto.springboot.proyecto.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proyecto.springboot.proyecto.bean.MyBean;
import com.proyecto.springboot.proyecto.bean.MyBean2Implement;
import com.proyecto.springboot.proyecto.bean.MyBeanWithDependency;
import com.proyecto.springboot.proyecto.bean.MyBeanWithDependencyImplement;
//import com.proyecto.springboot.proyecto.bean.MyBeanImplement;
import com.proyecto.springboot.proyecto.bean.MyOperation;
import com.proyecto.springboot.proyecto.bean.MyOperationImplement;

@Configuration
public class MyConfigurationBean {
  @Bean
  public MyBean beanOperation() {
    return new MyBean2Implement();
  }

  @Bean
  public MyOperation beanOperatioOperation() {
    return new MyOperationImplement();
  }

  @Bean
  public MyBeanWithDependency beanOperatioOperationWithDependency(MyOperation myOperation) {
    return new MyBeanWithDependencyImplement(myOperation);
  }
}
