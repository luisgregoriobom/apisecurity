package br.com.develfoodspringweb.develfoodspringweb.controller.dto;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import lombok.Data;

@Data
public class PlateDto {

    private Long id;
    private String name;
    private String obs;


    public PlateDto(Plate plate) {
        this.id = plate.getId();
        this.name = plate.getName();
        this.obs = plate.getObs();
    }

    /**
     * Function to convert the object Model class received into a DTO Object class
     * @param plate
     * @return
     * @author: Thomas B.P.
     */
    public static PlateDto convertToPlateDto(Plate plate){
        return new PlateDto(plate);
    }


}
