package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("plate")
public class PlateController {

    @Autowired
    private PlateRepository plateRepository;

    /**
     * Function with GET method to do make a query with the name of the plate as parameter.
     * @param namePlate
     * @return
     * @author: Thomas B.P.
     */
    @GetMapping
    public PlateDto getPlateByName(@RequestParam String namePlate){
        if(namePlate == null){
            return null;
        }

        Optional<Plate> opt = plateRepository.findByName(namePlate);
        if (!opt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Plate name not found");
        }

         return PlateDto.convertToPlateDto(opt.get());

    }

    /**
     * Function with POST method to register new Plate while the function create the URI route and return the head HTTP location with the URL
     * @param plateForm
     * @param uriBuilder
     * @return
     * @author: Thomas B.P.
     */
    @PostMapping
    public ResponseEntity<PlateDto> register(@RequestBody @Valid PlateForm plateForm,
                                             UriComponentsBuilder uriBuilder){
       Plate plate = plateForm.convertToPlate(plateForm);
       plateRepository.save(plate);

        URI uri = uriBuilder.
                path("/{id}").
                buildAndExpand(plate.getId()).
                toUri();

       return ResponseEntity.created(uri).body(new PlateDto(plate));
    }

}
