package com.apartmentbuilding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApartmentbuildingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentbuildingApplication.class, args);
	}

}
