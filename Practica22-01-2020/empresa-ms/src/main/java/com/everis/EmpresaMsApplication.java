package com.everis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.everis.util.CustomRepositoryImpl;

@EnableHystrixDashboard
@EnableHystrix //CircuitBreaker
@EnableFeignClients
//Para utilizar el CustomRespository añadimos esta anotación
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class EmpresaMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpresaMsApplication.class, args);
	}

}
