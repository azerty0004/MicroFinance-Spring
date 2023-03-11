package tn.esprit.infini.Pidev;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.esprit.infini.Pidev.Services.Creditservice;
import tn.esprit.infini.Pidev.entities.Credit;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class PidevApplication implements CommandLineRunner {
	private Creditservice creditservice;

	public static void main(String[] args) {
		SpringApplication.run(PidevApplication.class, args);
	}
	@Override
	public void run(String...args) throws Exception{
		List<Credit> credits = creditservice.retrieveAllCredits();
		credits.forEach(credit -> System.out.println(credit.getAmount()));
	}

}

