package br.com.develfoodspringweb.develfoodspringweb.controller.dto;


import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.PlateCategory;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlateDto {

    private Long id;
    private String name;
    private String description;


    public PlateDto(Plate plate) {
        this.id = plate.getId();
        this.description = plate.getDescription();
        this.name = plate.getName();
    }

    //Conversão de Plate para PlateDto
    public static PlateDto convertToDto(Plate plate){
        return new PlateDto(plate);
    }
}
