package tn.esprit.infini.Pidev.dto;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
@AllArgsConstructor

public class ScheduledTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Date startDate = new Date();

    @Scheduled(cron = "* * * ? * *") // Execute on the 1st day of every month at midnight
    public void executeMonthlyTask() {
        Date currentDate = new Date();

        if (currentDate.equals(startDate) || currentDate.after(startDate)) {
            // Your code here
            System.out.println("Scheduled task executed on " + dateFormat.format(new Date()));
        }
    }
}
