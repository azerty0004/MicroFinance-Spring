package tn.esprit.infini.Pidev.dto;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.infini.Pidev.Services.TransactionService;
import tn.esprit.infini.Pidev.entities.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private List<Transaction> transactionList;


    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");

    @Scheduled(cron = "* * * ? * *")
    public void reportCurrentTime() {
       System.out.println("");
    }
}