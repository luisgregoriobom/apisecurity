package br.com.develfoodspringweb.develfoodspringweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class DevelfoodSpringwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevelfoodSpringwebApplication.class, args);
	}

}
