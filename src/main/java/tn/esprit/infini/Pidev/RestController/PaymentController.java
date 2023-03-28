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
import tn.esprit.infini.Pidev.Services.ITransaction;
import tn.esprit.infini.Pidev.Services.TransactionService;
import tn.esprit.infini.Pidev.dto.CreatePayment;
import tn.esprit.infini.Pidev.dto.CreatePaymentResponse;
import tn.esprit.infini.Pidev.entities.CheckoutForm;
import tn.esprit.infini.Pidev.entities.Transaction;
import tn.esprit.infini.Pidev.entities.TypeTransaction;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
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




    @PostMapping("/create-payment-intent")
    public void createPaymentIntent(@NotNull @RequestBody  CreatePayment createPayment)throws StripeException {
        Stripe.apiKey=this.stripePublicKey;
        PaymentIntentCreateParams createParams = new
                PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .putMetadata("featureRequest", createPayment.getFeatureRequest())
                .setAmount(createPayment.getAmount()*100L)
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);


    }
    @PostMapping("confirm-payment-intent")
    public void confimPayment(@RequestBody String intentId) throws StripeException {
        Stripe.apiKey=this.stripePublicKey;
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(intentId, JsonObject.class);
        String id = jsonObject.get("intentId").getAsString();
        PaymentIntent intent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        intent.confirm(params);

        }

        @PostMapping("/persist-payment-base")
        public  void persistTransaction(@RequestBody String intentId) throws StripeException {
            Stripe.apiKey=this.stripePublicKey;
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(intentId, JsonObject.class);

            String id = jsonObject.get("id").getAsString();
            PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
            String status =paymentIntent.getStatus();
            Long userId = 1l;
            Date date = new Date();

            Long idObject = 1l;
            Long amount = paymentIntent.getAmount();
            String stripeid=paymentIntent.getId();
            String paymentMethod=paymentIntent.getPaymentMethod();


            TypeTransaction typeTransaction = TypeTransaction.Invest;

            if (status.equals("succeeded"))
            {
                Transaction transaction =new Transaction(typeTransaction,userId,idObject, date,amount,stripeid,paymentMethod);

                transactionService.addTransaction(transaction);



            }



        }






    @GetMapping("/pay")
    public String home(Model model){
        model.addAttribute("checkoutForm",new CheckoutForm());
        return "index";
    }
    @PostMapping("/pay")
    public String checkout(@ModelAttribute
                          CheckoutForm checkoutForm,
                           BindingResult bindingResult,
                           Model model){
        if (bindingResult.hasErrors()){
            return "index.html";
        }

        model.addAttribute("stripePublicKey",stripePublicKey);
        model.addAttribute("amount",checkoutForm.getAmount());
        model.addAttribute("email", checkoutForm.getEmail());
        model.addAttribute("featureRequest", checkoutForm.getFeatureRequest());
        return "checkout.html";
    }
}
