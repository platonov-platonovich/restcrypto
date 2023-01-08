package app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Poller {


    @Async
    @Scheduled(fixedRate = 15000)
    @Autowired
    public void times(){


        System.out.println("hay hay");

    }
}
