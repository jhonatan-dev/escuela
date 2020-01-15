package com.everis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.everis.util.CustomRepositoryImpl;

//Para utilizar el CustomRespository añadimos esta anotación
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
@SpringBootApplication
public class EscuelaConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscuelaConfigClientApplication.class, args);
	}

}
