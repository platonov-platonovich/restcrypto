package app;

import app.models.Cryptocurrency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;

//@EnableFeignClients|
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class CryptoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptoApplication.class, args);
//        RestTemplate restTemplate = new RestTemplate();
//        Cryptocurrency cryptocurrency = restTemplate.getForObject("https://api.coinlore.net/api/ticker/?id=90", Cryptocurrency.class);
//        System.out.println("Symbol:    " + cryptocurrency.getSymbol());
//        System.out.println("Price:   " + cryptocurrency.getPrice());
    }
}
