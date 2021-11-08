package br.com.develfoodspringweb.develfoodspringweb.controller.form;


import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RestaurantForm {

    @NotEmpty @NotNull @Length(min = 5)
    private String name;
    @NotNull @NotEmpty @Length(min = 11)
    private String cnpj;
    @NotNull @NotEmpty @Length(min = 5)
    private String login;
    @NotNull @NotEmpty @Length(min = 5)
    private String password;
    @NotNull @NotEmpty @Length(min = 5)
    private String email;
    @NotNull @NotEmpty @Length(min = 5)
    private String address;
    @NotNull @NotEmpty @Length(min = 11)
    private String phone;

    private List<Plate> plates;

    public RestaurantForm() {
    }

    public RestaurantForm(Restaurant restaurant){
        this.name = restaurant.getName();
        this.cnpj = restaurant.getCnpj();
        this.login = restaurant.getLogin();
        this.password = restaurant.getPassword();
        this.email = restaurant.getEmail();
        this.address = restaurant.getAddress();
        this.phone = restaurant.getPhone();

    }

    /**
     * Function to convert the object Form Class received into a Model Object
     * @param restaurantForm
     * @return
     * @author: Thomas B.P.
     */
    public Restaurant convertToRestaurant(RestaurantForm restaurantForm) {
        return new Restaurant(restaurantForm);
    }
}
