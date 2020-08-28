package br.com.mad.springboot2crudgm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Springboot2CrudGmApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2CrudGmApplication.class, args);
	}

}
