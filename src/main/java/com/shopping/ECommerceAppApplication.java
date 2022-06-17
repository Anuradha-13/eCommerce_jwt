package com.shopping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.modelmapper.ModelMapper;
@SpringBootApplication
public class ECommerceAppApplication {

	private static final Logger LOGGER=LoggerFactory.getLogger(ECommerceAppApplication.class);
	//configure ModelMapper class as Spring bean so that we can inject it in controller class:
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ECommerceAppApplication.class, args);
	}
	
	}
