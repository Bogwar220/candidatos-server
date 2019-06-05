package com.bstan.eje2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.bstan.eje2.controlador"})
@EntityScan("com.bstan.eje2.modelo")
@EnableJpaRepositories("com.bstan.eje2.repositories")
public class Eje2Application {

	public static void main(String[] args) {
		SpringApplication.run(Eje2Application.class, args);
	}

}
