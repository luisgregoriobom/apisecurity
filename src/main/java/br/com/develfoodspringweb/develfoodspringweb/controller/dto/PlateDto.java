package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlateDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
    private Restaurant restaurant;


    public PlateDto(Plate plate) {
        this.id = plate.getId();
        this.name = plate.getName();
        this.price = plate.getPrice();
        this.category = plate.getCategory();
        this.restaurant = plate.getRestaurant();
    }

    /**
     * Function to convert the object Model class received into a DTO Object class
     * @param plates
     * @return
     * @author: Thomas B.P.
     */
    public static PlateDto convertToPlateDto(Plate plates){
        return new PlateDto(plates);
    }

    //conversao de plateodto para lista de platedto
    public static List<PlateDto> convert(List<Plate> plates) {
        return plates.stream().map(PlateDto::new).collect(Collectors.toList());
    }
}
