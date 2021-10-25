package br.com.develfoodspringweb.develfoodspringweb.configuration;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.PlateCategory;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitialConfig {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private PlateRepository plateRepository;

    public void configurar(){
//        Plate plate = new Plate();
//        plate.setCategory(PlateCategory.SWEET);
//        plate.setName("Bolinho de cenoura");
//        plate.setDescription("Bolito de cenoura com chocolate");
//        plate.setPrice(new BigDecimal(23.05));
//        plateRepository.save(plate);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("testName");
        restaurant.setPhone("333");
        restaurantRepository.save(restaurant);

//        User user = new User();
//        user.setName("testeName1");
//        user.setPhone("111");
//        userRepository.save(user);
    }

}
