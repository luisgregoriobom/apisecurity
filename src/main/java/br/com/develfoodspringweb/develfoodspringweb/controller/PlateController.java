package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.UserDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.UserForm;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.User;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.UserRepository;
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
@RequestMapping("/plate")
public class PlateController {

    @Autowired
    private PlateRepository plateRepository;

    @GetMapping
    public PlateDto getUserByName(@RequestParam String namePlate){
        if(namePlate == null){ //validando o param da query
            return null;
        }

        Optional<Plate> opt = plateRepository.findByName(namePlate);
        if (!opt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Plate name not found");
        }
         return PlateDto.convertToPlateToDto(opt.get());

    }

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
