package app;

import app.models.Cryptocurrency;


import app.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FirstController {
@Autowired
private  CryptoRepository cryptoRepository;


    @RequestMapping("/cryptocurrencies")
    public List<Cryptocurrency> getAll() {
        System.out.println("as");
       return (List<Cryptocurrency>) cryptoRepository.findAll();

    }
@RequestMapping("/cryptocurrency/{id}")
public Optional<Cryptocurrency> getById(@PathVariable("id") String id) {
     return  cryptoRepository.findById(id);
}
}



