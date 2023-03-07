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
import tn.esprit.infini.Pidev.dto.CreatePayment;
import tn.esprit.infini.Pidev.dto.CreatePaymentResponse;
import tn.esprit.infini.Pidev.entities.Credit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@SpringBootApplication

public class PidevApplication implements CommandLineRunner {
	@Value("${stripe.api.key}")
	private String stripePublicKey;


	public static void main(String[] args) {
		SpringApplication.run(PidevApplication.class, args);
	}
	@Override
	public void run(String...args) throws Exception{
		Stripe.apiKey=stripePublicKey;
		PaymentController pc= new PaymentController();

		CreatePayment cr = new CreatePayment();
		cr.setAmount(5000);
		cr.setFeatureRequest("TEST");
		pc.createPaymentIntent(cr);

		/*Map<String, Object> params = new HashMap<>();
		params.put("limit", 3);

		PaymentIntentCollection paymentIntents =
				PaymentIntent.list(params);
		paymentIntents.toJson();*/







	}

}

