package com.thirumal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
/**
 * @author Thirumal
 */
@EnableAsync
@SpringBootApplication
public class PwnedPasswordApplication {

	public static void main(String[] args) {
		SpringApplication.run(PwnedPasswordApplication.class, args);
	}

}
