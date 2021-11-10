package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.dto.RestaurantDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.models.Restaurant;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantNameRepository;
import br.com.develfoodspringweb.develfoodspringweb.service.PlateService;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/plate")
public class PlateController {

    private final PlateRepository plateRepository;

    private final PlateService plateService;

    private final RestaurantNameRepository restaurantNameRepository;

    /**
     * Method GET to list all plates
     * @param plateName
     * @return
     * @author: Luis Gregorio
     */
    @GetMapping("/ListAll")
    @Transactional
    public List<PlateDto> list(String plateName) {
        if (plateName == null) {
            List<Plate> plates = plateRepository.findAll();
            return PlateDto.converter(plates);
        } else {
            List<Plate> plates = plateRepository.findByPlateName(plateName);
            return PlateDto.converter(plates);
        }
    }

    /**
     * Function with GET method to do make a query with the name of the plate as parameter.
     * @param namePlate
     * @return
     * @author: Thomas B.P.
     */
    @GetMapping
    public ResponseEntity<PlateDto> getPlateByName(@RequestParam String namePlate){
        if(namePlate == null){
            return null;
        }
        PlateDto queryByName = plateService.getPlateByName(namePlate);
        if (queryByName == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Plate name not found");
        }
        return new ResponseEntity<>(queryByName, HttpStatus.OK);

    }

    /**
     * Function with POST method to register new Plate while the function create the URI route and return the head HTTP location with the URL
     * @param form
     * @param uriBuilder
     * @return
     * @author: Thomas B.P.
     */
    @PostMapping
    public ResponseEntity<PlateDto> register(@RequestBody @Valid PlateForm form, UriComponentsBuilder uriBuilder) {
        Plate plate = form.convert(restaurantNameRepository);
        plateRepository.save(plate);
        URI uri = uriBuilder.path("/api/plate/{id}").buildAndExpand(plate.getId()).toUri();
        return ResponseEntity.created(uri).body(new PlateDto(plate));
    }
//    @PostMapping
//    public ResponseEntity<PlateDto> register(@RequestBody @Valid PlateForm plateForm,
//                                             UriComponentsBuilder uriBuilder){
//
//        PlateDto plateToRegister = plateService.register(plateForm);
//
//        URI uri = uriBuilder.
//                path("/api/plate/{id}").
//                buildAndExpand(plateToRegister.getId()).
//                toUri();
//
//        return ResponseEntity.created(uri).body(plateToRegister);
//    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<PlateDto> details(@PathVariable Long id) {
        PlateDto plateDetail = plateService.details(id);
        if(plateDetail == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Plate Not Found");
        }
            return ResponseEntity.ok(plateDetail);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PlateDto> update(@PathVariable Long id, @RequestBody @Valid PlateFormUpdate form) {
        PlateDto plateUpdate = plateService.update(id, form);
        if (plateUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Plate Not Found");
        }
        return ResponseEntity.ok(plateUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){
       PlateDto plateRemove = plateService.remove(id);
       if(plateRemove == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                   "Plate Not Found");
       }
       return ResponseEntity.ok().build();
    }
}