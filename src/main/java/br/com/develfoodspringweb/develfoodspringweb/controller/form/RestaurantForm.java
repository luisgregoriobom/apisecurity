package br.com.develfoodspringweb.develfoodspringweb.controller.form;


import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RestaurantForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String cnpj;
    @NotNull @NotEmpty
    private String phone;
    @NotNull @NotEmpty
    private String address;

    private List<Plate> plates;

    /**
     * Function to convert the object Form Class received into a Model Object
     * @param restaurantForm
     * @return
     * @author: Thomas B.P.
     */
    public Restaurant convertToRestaurant(RestaurantForm restaurantForm){
        return new Restaurant(restaurantForm);
    }
}
