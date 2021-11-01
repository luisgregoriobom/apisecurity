package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/restaurant")
public class PlateController {

    public List<PlateDto> list() {
        Plate plate = new Plate("plate", "plate");

        return PlateDto.convert(Arrays.asList(plate, plate, plate));
    }


}