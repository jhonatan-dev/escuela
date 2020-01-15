package com.everis;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.everis.util.CustomRepositoryImpl;

//Para utilizar el CustomRespository añadimos esta anotación
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
@EnableEurekaClient
public class ProductoMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductoMsApplication.class, args);
	}

}
