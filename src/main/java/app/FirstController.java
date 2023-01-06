package app;
import app.dao.PostgresCryptoDao;
import app.models.Cryptocurrency;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FirstController {


    private final PostgresCryptoDao postgresCryptoDao;

    public FirstController(PostgresCryptoDao postgresCryptoDao) {
        this.postgresCryptoDao = postgresCryptoDao;
    }

    @RequestMapping("/cryptocurrencies")
    public List<Cryptocurrency> getAll() {
        System.out.println("as");
        return postgresCryptoDao.getAll();
    }
@RequestMapping("/get")
public List<Cryptocurrency> getRestTemplate() {
     return  null;
}
}



