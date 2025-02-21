package org.project.InventoryManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.project.InventoryManagementSystem.entity")
public class InventoryManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventoryManagementSystemApplication.class, args);
	}

}
