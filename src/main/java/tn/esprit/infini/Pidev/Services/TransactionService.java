package tn.esprit.infini.Pidev.Services;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteTransaction(Long idTransaction) {
        transactionRepository.deleteById(idTransaction);

    }

    @Override
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

    @Override
    public List<Date> extractDates(List<Transaction> transactionList) {
            List<Date> dates = new ArrayList<>();
            for (Transaction transaction : transactionList) {
                dates.add(transaction.getDate());
            }
            return dates;

    }

    @Override
    public List<String> DatesToCronExpressions(List<Date> dates) {
        List<String> cronExpressions = new ArrayList<>();

        for (Date date : dates) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int minutes = calendar.get(Calendar.MINUTE);
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH) + 1;
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            SimpleDateFormat dateFormat = new SimpleDateFormat("ss");
            String seconds = dateFormat.format(date);

            String cronExpression = String.format("%s %s %s %s %s ?",
                    seconds, minutes, hours, dayOfMonth, month);
            if (dayOfWeek != 0) {
                cronExpression += " " + dayOfWeek;
            }

            cronExpressions.add(cronExpression);
        }

        return cronExpressions;
    }


    }

