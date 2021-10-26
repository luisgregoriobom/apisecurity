package br.com.develfoodspringweb.develfoodspringweb;

import br.com.develfoodspringweb.develfoodspringweb.configuration.InitialConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableSpringDataWebSupport
public class DevelfoodSpringwebApplication {

	@Autowired
	private InitialConfig initialConfig;

	public static void main(String[] args) {
		SpringApplication.run(DevelfoodSpringwebApplication.class, args);

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
