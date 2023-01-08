package app.repositories;

import app.models.Cryptocurrency;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CryptoRepository extends CrudRepository<Cryptocurrency, String> {

 @Override
List<Cryptocurrency> findAll();
}
//    @Override
//    public List<Cryptocurrency> getAll() {
//        List<Cryptocurrency> cryptocurrencies = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM cryptocurrency";
//            ResultSet resultSet = statement.executeQuery(SQL);
//            while (resultSet.next()) {
//                Cryptocurrency cryptocurrency = new Cryptocurrency();
//                cryptocurrency.setId(resultSet.getString("id"));
//                cryptocurrency.setSymbol(resultSet.getString("symbol"));
//                cryptocurrency.setPrice_usd(resultSet.getString("price_usd"));
//                cryptocurrencies.add(cryptocurrency);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return cryptocurrencies;
//    }
//
//
//    public List<Cryptocurrency> getActualCryptocurrencies() {
//        RestTemplate restTemplate = new RestTemplate();
//        String[] cryptocurrenciesId = {"90", "80", "48543"};
//        List<Cryptocurrency> cryptocurrenciesServer = new ArrayList<>();
//        for (String id : cryptocurrenciesId) {
//            String url = "https://api.coinlore.net/api/ticker/?id=%s";
//            url = String.format(url, id);
//            Cryptocurrency[] forObject = restTemplate.getForObject(url, Cryptocurrency[].class);
//            List<Cryptocurrency> cryptocurrencies = Arrays.asList(forObject);
//            for (Cryptocurrency cryptocurrency : cryptocurrencies) {
//                if (cryptocurrency.getId().equals(id)) {
//                    cryptocurrenciesServer.add(cryptocurrency);
//                }
//            }
//        }
//        return cryptocurrenciesServer;
//    }
//
//    public boolean comparison() {
//        boolean equal = true;
//        List<Cryptocurrency> cryptocurrenciesBD = getActualCryptocurrencies();
//        List<Cryptocurrency> cryptocurrenciesServer = getActualCryptocurrencies();
//        for (Cryptocurrency cryptocurrencyBD : cryptocurrenciesBD) {
//            for (Cryptocurrency cryptocurrencyServer : cryptocurrenciesServer) {
//                if (cryptocurrencyServer.getId().equals(cryptocurrencyBD.getId())) {
//                    if (cryptocurrencyBD.getPrice_usd().equals(cryptocurrencyServer.getPrice_usd())) {
//                        equal = false;
//                    }
//
//                }
//            }
//
//        }
//        return equal;
//    }
//
//
//    public void update() {
//        if (comparison()) {
//            RestTemplate restTemplate = new RestTemplate();
//            String[] cryptocurrenciesId = {"90", "80", "48543"};
//            for (String id : cryptocurrenciesId) {
//                String url = "https://api.coinlore.net/api/ticker/?id=%s";
//                url = String.format(url, id);
//                Cryptocurrency[] forObject = restTemplate.getForObject(url, Cryptocurrency[].class);
//                List<Cryptocurrency> cryptocurrencies = Arrays.asList(forObject);
//                for (Cryptocurrency cryptocurrency : cryptocurrencies) {
//                    if (cryptocurrency.getId().equals(id)) {
//                        try {
//                            PreparedStatement preparedStatement =
//                                    connection.prepareStatement("UPDATE cryptocurrency SET price_usd=? WHERE id=?");
//                            preparedStatement.setString(1, cryptocurrency.getPrice_usd());
//                            preparedStatement.setString(2, id);
//                            preparedStatement.executeUpdate();
//                        } catch (SQLException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
