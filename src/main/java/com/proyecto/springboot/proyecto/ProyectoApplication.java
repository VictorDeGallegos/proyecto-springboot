package com.proyecto.springboot.proyecto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.proyecto.springboot.proyecto.bean.MyBean;
import com.proyecto.springboot.proyecto.bean.MyBeanWithDependency;
import com.proyecto.springboot.proyecto.bean.MyBeanWithProperties;
import com.proyecto.springboot.proyecto.component.ComponentDependency;
import com.proyecto.springboot.proyecto.entity.User;
import com.proyecto.springboot.proyecto.pojo.UserPojo;
import com.proyecto.springboot.proyecto.repository.UserRepository;
import com.proyecto.springboot.proyecto.service.UserService;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(ProyectoApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	// * Indicar que componente se va a utilizar
	public ProyectoApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
			MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties,
			UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}

	private void getInformationJpqlFromUser() {
		LOGGER.info("Usuario con el metodo findByUserEmail" +
				userRepository.findMyUserByEmail("juan@domain.com")
						.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		userRepository.findByAndSort("Victor", Sort.by("id").descending()).stream()
				.forEach(user -> LOGGER.info("Usuario con metodo sort " + user));

		userRepository.findByNameContaining("Juan").stream()
				.forEach(user -> LOGGER.info("Usuario con query method findByName " + user));

		LOGGER.info("Usuario con query method findByEmailAndName"
				+ userRepository.findUsersByNameAndAndEmail("Daniela", "daniela@domain.com")
						.orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%M%").stream()
				.forEach(user -> LOGGER.info("Usuario con query method findByNameLike " +
						user));

		userRepository.findByNameOrEmail(null, "teresa@domain.com").stream()
				.forEach(user -> LOGGER.info("Usuario con query method findByNameOrEmail" +
						user));

		userRepository.findByBirthDateBetween(LocalDate.of(2021, 01, 30), LocalDate.of(2021, 12, 30)).stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas findByBirthDateBetween " + user));

		userRepository.findByNameLikeOrderByIdDesc("%Juan%").stream()
				.forEach(user -> LOGGER
						.info("Usuario encontrado con like y ordenado findByOrderByIdDescending " +
								user));

		userRepository.findByNameContainingOrderByIdDesc("Juan").stream()
				.forEach(user -> LOGGER
						.info("Usuario encontrado con like y ordenado findByNameContainingOrderByIdDesc " +
								user));

		// LOGGER.info("Usuario a partir de named parameter es: "
		// + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 03, 25),
		// "daniela@domain.com")
		// .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

	}

	private void saveWithErrorTransactional() {

		User test1 = new User("test1Transactional1", "test1Transactional1@domain.com", LocalDate.now());
		User test2 = new User("test2Transactional1", "test2Transactional1@domain.com", LocalDate.now());
		User test3 = new User("test3Transactional1", "test1Transactional1@domain.com", LocalDate.now());
		User test4 = new User("test4Transactional1", "test4Transactional1@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		try {
			userService.saveTransactional(users);
		} catch (Exception e) {
			LOGGER.error("Esta es una excepcion dentro del metodo transactional", e);
		}

		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Usuario con metodo saveTransactional " + user));

	}

	// * Metodo para persistir informacion
	private void saveUsersInDataBase() {
		User user1 = new User("Juan", "juan@domain.com", LocalDate.of(2021, 03, 20));
		User user2 = new User("Juan", "miguel@domain.com", LocalDate.of(2021, 05, 21));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 03, 25));
		User user4 = new User("Oscar", "oscar@domain.com", LocalDate.of(2021, 9, 25));
		User user5 = new User("Victor", "victor@domain.com", LocalDate.of(2021, 10, 24));
		User user6 = new User("Hugo", "hugo@d omain.com", LocalDate.of(2021, 10, 20));
		User user7 = new User("Karla", "karla@domain.com", LocalDate.of(2021, 11, 17));
		;
		User user8 = new User("Maria", "maria@domain.com", LocalDate.of(2021, 01, 20));
		User user9 = new User("Teresa", "teresa@domain.com", LocalDate.of(2021, 02, 20));
		User user10 = new User("Adrian", "adrian@domain.com", LocalDate.of(2021, 10, 20));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores() {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
		LOGGER.error("Esto es un Error");
		LOGGER.debug("Esto es un Debug");
	}

}
