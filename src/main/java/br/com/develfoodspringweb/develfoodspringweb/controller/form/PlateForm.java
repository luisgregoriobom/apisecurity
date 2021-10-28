package br.com.develfoodspringweb.develfoodspringweb.controller.form;

import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Category;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PlateForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String obs;
    @NotNull @NotEmpty
    private Category category;

    public Plate convertToPlate(PlateForm plateForm){
        return new Plate(plateForm);
    }

}
