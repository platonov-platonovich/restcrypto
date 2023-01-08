package app.repositories;

import app.models.Cryptocurrency;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CryptoRepository extends CrudRepository<Cryptocurrency, String> {

 @Override
List<Cryptocurrency> findAll();

 @Override
 Optional<Cryptocurrency> findById(String s);

 @Override
Cryptocurrency save(Cryptocurrency entity);
}
