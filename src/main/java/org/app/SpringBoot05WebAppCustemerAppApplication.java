package org.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBoot05WebAppCustemerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot05WebAppCustemerAppApplication.class, args);
	}

}
