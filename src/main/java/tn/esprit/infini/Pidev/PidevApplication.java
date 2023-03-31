package tn.esprit.infini.Pidev;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories(basePackages = "tn.esprit.infini.Pidev.Repository")
@AllArgsConstructor
public class PidevApplication  {

	public static void main(String[] args) {
		SpringApplication.run(PidevApplication.class, args);
	}


}

