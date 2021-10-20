package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;

public class RestaurantDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Plate plate;

    public RestaurantDto(Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.plate = plate;
    }
}
