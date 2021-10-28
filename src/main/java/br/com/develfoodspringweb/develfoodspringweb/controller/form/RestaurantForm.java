package br.com.develfoodspringweb.develfoodspringweb.controller.form;


import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class RestaurantForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String cnpj;
    @NotNull @NotEmpty
    private String login;
    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String phone;
    @NotNull @NotEmpty
    private String address;

    public Restaurant convertToRestaurant(RestaurantForm restaurantForm){
        return new Restaurant(restaurantForm);
    }
}
