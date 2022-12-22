package app.service;

import app.dao.PostgresCryptoDao;

import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Poller {


    @Async
    @Scheduled(fixedRate = 15000)
    public void times(){
        PostgresCryptoDao cryptoDao = new PostgresCryptoDao();
        cryptoDao.update();
        System.out.println("hay hay");

//    public Cryptocurrency scheduleFixedRateTaskAsync(Cryptocurrency oldValue, Cryptocurrency newValue) {
//
//        if (newValue.equals(oldValue)) {
//            System.out.println("hay hay");
//            return oldValue;
//        } else {
//            System.out.println("hay hay");
//            return newValue;
//
//        }
    }
}
