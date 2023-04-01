package tn.esprit.infini.Pidev.Services;

import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.esprit.infini.Pidev.Repository.TransactionRepository;
import tn.esprit.infini.Pidev.entities.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TransactionService implements ITransaction {
    @Value("${stripe.api.key}")
    private String stripePublicKey;
    @Autowired
    private TransactionRepository transactionRepository;

    private static Gson gson = new Gson();
    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> retrieveAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction retrieveTransaction(Long idTransaction) {
        return transactionRepository.findById(idTransaction).get();
    }

    @Override
    public Transaction retrieveTransactionByStripeId(String stripeId) {
        return transactionRepository.findByStripeId(stripeId);
    }


    @Override
    public void deleteTransaction(Long idTransaction) {
        transactionRepository.deleteById(idTransaction);

    }

    @Override

    public List<Transaction> divideTransaction(Long amount, Integer numberOfMonths) {
        List<Transaction> transactionList = new ArrayList<>();

        BigDecimal loanAmountInBigDecimal = BigDecimal.valueOf(amount);
        BigDecimal monthlyPayment = loanAmountInBigDecimal.divide(BigDecimal.valueOf(numberOfMonths), 2, RoundingMode.HALF_UP);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        for (int i = 1; i <= numberOfMonths; i++) {
            Transaction payment = new Transaction();
            payment.setAmount(monthlyPayment.longValue());

            // Set date for each transaction with a 1-minute increment
            calendar.add(Calendar.SECOND, 40);
            System.out.println(calendar.getTime());
            payment.setDate(calendar.getTime());

            transactionList.add(payment);
        }

        return transactionList;
    }




    @Override
    public List<Transaction> getTransactionsRequiringPayment() {
        return transactionRepository.findByStatus("requires_payment_method");
    }

    @Override
    public void confirmTransaction(String intentId) {
       /* JsonObject jsonObject = gson.fromJson(intentId, JsonObject.class);

        String id = jsonObject.get("intentId").getAsString();*/

        transactionRepository.updateTransactionStatusByStripeId("Succeded",intentId);
        transactionRepository.updateTransactionPaymentMethodByStripeId("visa_credit_card",intentId);


    }


    public List<Transaction> divideTransaction(Long amount,Integer numberOfMonths) {
        List<Transaction> transactionList=new ArrayList<>();

            BigDecimal loanAmountInBigDecimal = BigDecimal.valueOf(amount);
            BigDecimal monthlyPayment = loanAmountInBigDecimal.divide(BigDecimal.valueOf(numberOfMonths), 2, RoundingMode.HALF_UP);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            System.out.println();
            for (int i = 1; i <= numberOfMonths; i++) {
                Transaction payment = new Transaction();
                payment.setAmount(monthlyPayment.longValue());
                payment.setDate(calendar.getTime());
                calendar.add(Calendar.MONTH, 1);
                transactionList.add(payment);
            }
            return (transactionList);
        }

}

