package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import lombok.Data;

@Data
public class RestaurantDto {

    private Long id;
    private String name;
    private String cnpj;
    private String phone;
    private String address;


    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.cnpj = restaurant.getCnpj();
        this.phone = restaurant.getPhone();
        this.address = restaurant.getAddress();
    }

    //Conversão de Restaurant para RestaurantDto
    public static RestaurantDto convertToRestaurantDto(Restaurant restaurant){
        return new RestaurantDto(restaurant);
    }
}
