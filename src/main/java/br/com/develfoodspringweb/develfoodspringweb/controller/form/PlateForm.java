package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class PlateForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String description;



    public Plate convertToPlate(){
        return new Plate(name, description);
    }
}
