package com.moneylion.assigment.featureswitch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FeatureSwitchApplication {
	private static final Logger log = LoggerFactory.getLogger(FeatureSwitchApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FeatureSwitchApplication.class, args);
	}

}
