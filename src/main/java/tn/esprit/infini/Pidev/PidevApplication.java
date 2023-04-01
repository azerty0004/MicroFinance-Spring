package tn.esprit.infini.Pidev;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntentCollection;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.esprit.infini.Pidev.RestController.PaymentController;
import tn.esprit.infini.Pidev.Services.Creditservice;

import tn.esprit.infini.Pidev.entities.Credit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@SpringBootApplication
public class PidevApplication  {
	@Value("${stripe.api.key}")
	private String stripePublicKey;


	public static void main(String[] args) throws IOException {
		SpringApplication.run(PidevApplication.class, args);

	}













	}



