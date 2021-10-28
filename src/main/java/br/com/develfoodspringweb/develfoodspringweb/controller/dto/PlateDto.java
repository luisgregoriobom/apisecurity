package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Menu;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import lombok.Data;

@Data
public class PlateDto {

    private Long id;
    private String name;
    private String obs;
    private Menu menu;

    public PlateDto(Plate plate) {
        this.id = plate.getId();
        this.name = plate.getName();
        this.obs = plate.getObs();
        this.menu = plate.getMenu();
    }

    //Conversão de Restaurant para RestaurantDto
    public static PlateDto convertPlateToDto(Plate plate){
        return new PlateDto(plate);
    }
}
