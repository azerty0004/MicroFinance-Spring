package tn.esprit.infini.Pidev;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntentCollection;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.infini.Pidev.RestController.PaymentController;
import tn.esprit.infini.Pidev.Services.Creditservice;
import tn.esprit.infini.Pidev.Services.ITransaction;
import tn.esprit.infini.Pidev.Services.TransactionService;
import tn.esprit.infini.Pidev.dto.CreatePayment;
import tn.esprit.infini.Pidev.dto.CreatePaymentResponse;
import tn.esprit.infini.Pidev.dto.ScheduledTasks;
import tn.esprit.infini.Pidev.entities.Credit;
import tn.esprit.infini.Pidev.entities.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@SpringBootApplication
/*@EnableScheduling*/
public class PidevApplication  {
	@Value("${stripe.api.key}")
	private String stripePublicKey;





	public static void main(String[] args) {
		SpringApplication.run(PidevApplication.class, args);
		TransactionService transactionService= new TransactionService();
		List<Transaction> transactionList=transactionService.divideTransaction(2000L,10);
		List<Date> dates= transactionService.extractDates(transactionList);
		List<String> CronExpressions=transactionService.DatesToCronExpressions(dates);
		System.out.println(CronExpressions);






		}
	}

















