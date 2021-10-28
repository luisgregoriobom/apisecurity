package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Menu;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import lombok.Data;

@Data
public class MenuDto {

    private Long id;
    private String name;
    private String obs;
    private Menu menu;

    public MenuDto(Plate plate) {
        this.id = plate.getId();
        this.name = plate.getName();
        this.obs = plate.getObs();
        this.menu = plate.getMenu();
    }

    //Conversão de Restaurant para RestaurantDto
    public static MenuDto convertPlateToDto(Plate plate){
        return new MenuDto(plate);
    }
}
