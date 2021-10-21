package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Plate plate;

    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.email = restaurant.getEmail();
        this.phone = restaurant.getPhone();
//        this.plate = restaurant.getPlates();
    }

    //Método para converter Restaurant em RestaurantDto
//    public static RestaurantDto convertToDto(Restaurant restaurant);



}
