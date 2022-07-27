package com.proyecto.springboot.proyecto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyecto.springboot.proyecto.bean.MyBean;
import com.proyecto.springboot.proyecto.bean.MyBeanWithDependency;
import com.proyecto.springboot.proyecto.bean.MyBeanWithProperties;
import com.proyecto.springboot.proyecto.component.ComponentDependency;
import com.proyecto.springboot.proyecto.pojo.UserPojo;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(ProyectoApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;

	// * Indicar que componente se va a utilizar
	public ProyectoApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
			MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
			UserPojo userPojo) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
		LOGGER.error("Esto es un Error");
		LOGGER.debug("Esto es un Debug");
	}

}
