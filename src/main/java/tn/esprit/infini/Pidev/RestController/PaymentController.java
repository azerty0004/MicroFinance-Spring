package tn.esprit.infini.Pidev.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.Services.IPayment;
import tn.esprit.infini.Pidev.Services.ITransaction;
import tn.esprit.infini.Pidev.Services.PaymentService;
import tn.esprit.infini.Pidev.Services.TransactionService;
import tn.esprit.infini.Pidev.dto.CreatePayment;
import tn.esprit.infini.Pidev.dto.CreatePaymentResponse;
import tn.esprit.infini.Pidev.entities.CheckoutForm;
import tn.esprit.infini.Pidev.entities.Transaction;
import tn.esprit.infini.Pidev.entities.TypeTransaction;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@NoArgsConstructor
public class PaymentController {
    @Value("${stripe.api.key}")
    private String stripePublicKey;
    private static Gson gson = new Gson();
    @Autowired
    private ITransaction transactionService;
    @Autowired
    private IPayment paymentService;




    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@NotNull @RequestBody  Transaction transaction)throws StripeException {
        Stripe.apiKey=this.stripePublicKey;
       String IntentId =paymentService.createPaymentIntent(transaction);
       paymentService.persistTransaction(IntentId);
       return IntentId;





    }
    @PostMapping("confirm-payment-intent")
    public void confimPayment(@RequestBody String intentId) throws StripeException {
        Stripe.apiKey=this.stripePublicKey;
        paymentService.confimPayment(intentId);
        transactionService.confirmTransaction(intentId);


        }

        @PostMapping("/persist-payment-base")
        public  void persistTransaction(@RequestBody String intentId) throws StripeException {
            Stripe.apiKey=this.stripePublicKey;
            paymentService.persistTransaction(intentId);





        }
    @GetMapping("/GetPaymentModel/{amount}/{numberOfMonths}")
    public void divideTransaction(@PathVariable Long amount, @PathVariable Integer numberOfMonths) throws StripeException {
        List<Transaction> maListe =transactionService.divideTransaction(amount, numberOfMonths);
        for (Transaction transaction:maListe)
        {
           String intent= paymentService.createPaymentIntent(transaction);
           transaction.setStripeId(intent);
           transaction.setStatus("requires_payment_method");
           transaction.setTypeTransaction(TypeTransaction.Invest);
            transactionService.addTransaction(transaction);

        }


    }







}
