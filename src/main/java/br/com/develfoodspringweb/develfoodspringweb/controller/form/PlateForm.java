package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantNameRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PlateForm {


    @NotEmpty @NotNull @Length(min = 5)
    private String name;
    @NotEmpty @NotNull @Length(min = 10)
    private String obs;
    @DecimalMin(value = "5.0", inclusive = false)
    private BigDecimal price;
    private Category category;
    private String nameRestaurant;



    /**
     * Function to convert the object Form Class received into a Model Object.
     * @param
     * @return
     * @author: Thomas B.P.
     */

    //interface NameRestaurantRepository para buscar o nome do restaurante
    // sem que atrele a busca a sua entidade, e sim somente ao seu nome.

    public Plate convert(RestaurantNameRepository restaurantNameRepository) {
        Restaurant restaurant = restaurantNameRepository.findByName(nameRestaurant);
    return new Plate(name, obs, price, category, restaurant);
    }

}
