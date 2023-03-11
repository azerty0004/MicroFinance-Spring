package tn.esprit.infini.Pidev.dto;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentResponse {
    private String clientSecret;

    public CreatePaymentResponse(PaymentIntent paymentIntent) {
        this.clientSecret = paymentIntent.getClientSecret();
    }
        public CreatePaymentResponse(String a )
        { this.clientSecret = a;}

    }









