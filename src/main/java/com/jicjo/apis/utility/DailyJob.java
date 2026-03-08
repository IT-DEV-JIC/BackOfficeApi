package com.jicjo.apis.utility;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Component
public class DailyJob implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Scheduled(cron = "0 0 18 * * ?")
    public void runEveryDayAt6PM() {
        System.out.println("Job started at: " + LocalDateTime.now());
        // Your business logic here
    }
}
