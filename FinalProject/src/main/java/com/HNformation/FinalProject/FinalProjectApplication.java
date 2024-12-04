package com.HNformation.FinalProject;

import com.HNformation.FinalProject.entity.User;
import com.HNformation.FinalProject.entity.UserType;
import com.HNformation.FinalProject.repository.UserRepository;
import com.HNformation.FinalProject.repository.UserTypeRepository;
import com.HNformation.FinalProject.service.UserService;
import com.HNformation.FinalProject.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.HNformation.FinalProject")
@EntityScan(basePackages = "com.HNformation.FinalProject.entity")
@EnableJpaRepositories(basePackages = "com.HNformation.FinalProject.repository")
public class FinalProjectApplication implements CommandLineRunner {

	private final UserService userService;
	private final UserTypeService userTypeService;

	@Autowired
	public FinalProjectApplication(UserService userService, UserTypeService userTypeService) {
		this.userService = userService;
		this.userTypeService = userTypeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Hello World!");
		// ajouter des user types et des user par défaut (s'il n'existe pas déjà)
		try {
			UserType userType1 = userTypeService.saveNewUserType(new UserType("commercial"));
			UserType userType2 = userTypeService.saveNewUserType(new UserType("admin"));
			userService.saveNewUser(new User("Le Goffic", "Liam", "liam.legoffic@mail.com", userType1));
			userService.saveNewUser(new User("Doe", "John", "john.doe@mail.com", userType2));
		} catch (Exception ignored) {}
	}

}
