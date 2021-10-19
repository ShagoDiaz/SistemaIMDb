package com.edutecno.imdb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.edutecno.imdb.dao.RoleRepository;
import com.edutecno.imdb.model.Role;

@SpringBootApplication
public class SistemaIMDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaIMDbApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(RoleRepository roleRepo) {
		return (args) -> {
			Role userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepo.save(userRole);

			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepo.save(adminRole);
		};
	}
}
