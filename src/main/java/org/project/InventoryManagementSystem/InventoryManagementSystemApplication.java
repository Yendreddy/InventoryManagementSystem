package org.project.InventoryManagementSystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = "org.project.InventoryManagementSystem.entity")
public class InventoryManagementSystemApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(InventoryManagementSystemApplication.class, args);
	}

}
