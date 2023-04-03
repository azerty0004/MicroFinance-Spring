package tn.esprit.infini.Pidev;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntentCollection;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.esprit.infini.Pidev.RestController.PaymentController;
import tn.esprit.infini.Pidev.Services.Creditservice;
import tn.esprit.infini.Pidev.Services.ITransaction;
import tn.esprit.infini.Pidev.Services.TransactionService;
import tn.esprit.infini.Pidev.dto.CreatePayment;
import tn.esprit.infini.Pidev.dto.CreatePaymentResponse;
import tn.esprit.infini.Pidev.dto.ScheduledTask;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SpringBootApplication
@EnableJpaRepositories(basePackages = "tn.esprit.infini.Pidev.Repository")
@AllArgsConstructor
@EnableScheduling
public class PidevApplication  {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(PidevApplication.class, args);


		ScheduledTask task = new ScheduledTask();
		task.executeMonthlyTask();





		}
	}


















