package tn.esprit.infini.Pidev.RestController;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tn.esprit.infini.Pidev.dto.CreatePayment;
import tn.esprit.infini.Pidev.dto.CreatePaymentResponse;
import tn.esprit.infini.Pidev.entities.CheckoutForm;

import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
@NoArgsConstructor
public class PaymentController {
    @Value("${stripe.api.key}")
    private String stripePublicKey;
    private static Gson gson = new Gson();



    @RequestMapping ("/create-payment-intent")
    public String createPaymentIntent(@NotNull @RequestBody  CreatePayment createPayment)throws StripeException {

        PaymentIntentCreateParams createParams = new
                PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .putMetadata("featureRequest", createPayment.getFeatureRequest())
                .setAmount(createPayment.getAmount() * 100L)
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);
        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());


        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        intent.confirm(params);
           return gson.toJson(paymentResponse);



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
