package app;

import app.dao.PostgresCryptoDao;
import app.models.Cryptocurrency;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class FirstController {


    private final PostgresCryptoDao postgresCryptoDao;

    public FirstController(PostgresCryptoDao postgresCryptoDao) {
        this.postgresCryptoDao = postgresCryptoDao;
    }

    @RequestMapping("/cryptocurrencies")
    public List<Cryptocurrency> getAll() {
        return postgresCryptoDao.getAll();
    }
    @RequestMapping("/n")
    public Cryptocurrency getC() {
                RestTemplate restTemplate = new RestTemplate();
        Cryptocurrency cryptocurrency = restTemplate.getForObject("https://api.coinlore.net/api/ticker/?id=90", Cryptocurrency.class);
        System.out.println("Symbol:    " + cryptocurrency.getSymbol());
        System.out.println("Price:   " + cryptocurrency.getPrice());
        return cryptocurrency;
}
}


