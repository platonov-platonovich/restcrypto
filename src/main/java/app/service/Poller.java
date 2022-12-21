package app.service;

import app.models.Cryptocurrency;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class Poller {

    @Async
    @Scheduled(fixedRate = 15000)
    public Cryptocurrency scheduleFixedRateTaskAsync(Cryptocurrency oldValue, Cryptocurrency newValue) {

        if (newValue.equals(oldValue)) {
            System.out.println("hay hay");
            return oldValue;
        } else {
            System.out.println("hay hay");
            return newValue;

        }
    }
}
