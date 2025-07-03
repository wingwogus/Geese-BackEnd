package goorm.geese;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GeeseApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeeseApplication.class, args);
	}

}
