package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;


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

    /**
     * Function to convert the object Model class received into a DTO Object class
     * @param restaurant
     * @return
     * @author: Thomas B.P.
     */
    public static RestaurantDto convertToRestaurantDto(Restaurant restaurant){
        return new RestaurantDto(restaurant);
    }


}
