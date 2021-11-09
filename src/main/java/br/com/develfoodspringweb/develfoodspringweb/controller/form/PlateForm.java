package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.Long;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@Data
public class PlateForm {


    @NotEmpty @NotNull @Length(min = 5)
    private String name;
    @NotEmpty @NotNull @Length(min = 10)
    private String obs;
    @DecimalMin(value = "5.0", inclusive = false)
    private BigDecimal price;
    private Category category;
    private Long restaurantId;

    @Autowired
    private RestaurantRepository restaurantRepository;


    /**
     * Function to convert the object Form Class received into a Model Object.
     * @param plateForm
     * @return
     * @author: Thomas B.P.
     */
    public Plate convertToPlate(PlateForm plateForm){
        return new Plate(plateForm);
    }

    //interface NameRestaurantRepository para buscar o nome do restaurante
    // sem que atrele a busca a sua entidade, e sim somente ao seu nome.
//    public Plate convert(PlateForm plateForm) {
//        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
//        var newRestaurant = new Restaurant();
//        if(restaurant.isPresent()) {
//            newRestaurant = restaurant.get();
//        }
//    return new Plate(name, obs, price, category, newRestaurant);
//    }

// Função pra converter form pra model e puxar já ao ID do restaurant - vai ser refeita!!!
//    public Plate convert(RestaurantRepository restaurantRepository) {
//        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
//        var newRestaurant = new Restaurant();
//        if(restaurant.isPresent()) {
//            newRestaurant = restaurant.get();
//        }
//    return new Plate(name, obs, price, category, newRestaurant);
//    }

}
