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
    private String login;
    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String phone;

    public Restaurant convertToRestaurant(){
        return new Restaurant(name, login, password, email, phone);
    }
}
