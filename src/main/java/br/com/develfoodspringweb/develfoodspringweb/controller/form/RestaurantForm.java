package br.com.develfoodspringweb.develfoodspringweb.controller.form;


import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
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

    public Restaurant convertToRestaurant(RestaurantForm restaurantForm){
        return new Restaurant(restaurantForm);
    }
}
