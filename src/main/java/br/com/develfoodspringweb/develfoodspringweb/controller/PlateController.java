package br.com.develfoodspringweb.develfoodspringweb.controller;

import br.com.develfoodspringweb.develfoodspringweb.controller.dto.PlateDto;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateForm;
import br.com.develfoodspringweb.develfoodspringweb.controller.form.PlateFormUpdate;
import br.com.develfoodspringweb.develfoodspringweb.models.Plate;
import br.com.develfoodspringweb.develfoodspringweb.repository.PlateRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantNameRepository;
import br.com.develfoodspringweb.develfoodspringweb.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by Luis Gregorio.
 *
 * Class that performs the methods of listing, registering, detailing, updating and removing from a plates.
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/plate")
public class PlateController {

    private final PlateRepository plateRepository;
    private final RestaurantNameRepository restaurantNameRepository;

    /**
     * Method to list all database plates
     * @param restaurantName
     * @return
     * @author: Luis Gregorio
     */
    @GetMapping
    public List<PlateDto> list(String restaurantName) {
        if (restaurantName == null) {
            List<Plate> plates = plateRepository.findAll();
            return PlateDto.convert(plates);
        } else {
            List<Plate> plates = plateRepository.findByName(restaurantName);
            return PlateDto.convert(plates);
        }
    }

    /**
     * Method to register a new plate in the database.
     * @param form
     * @param uriBuilder
     * @return
     * @author: Luis Gregorio
     */
    @PostMapping
    public ResponseEntity<PlateDto> register(@RequestBody @Valid PlateForm form, UriComponentsBuilder uriBuilder) {
        Plate plate = form.convert(restaurantNameRepository);
        plateRepository.save(plate);
        URI uri = uriBuilder.path("/api/plate/{id}").buildAndExpand(plate.getId()).toUri();
        return ResponseEntity.created(uri).body(new PlateDto(plate));
    }

    /**
     * Method to detail an already registered plate.
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<PlateDto> details(@PathVariable Long id) {
        Optional<Plate> plate = plateRepository.findById(id);
        if (plate.isPresent()) {
            return ResponseEntity.ok(new PlateDto(plate.get()));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Method for updating the data of an already registered plate.
     * @param id
     * @param form
     * @return
     * @author: Luis Gregorio
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PlateDto> update(@PathVariable Long id, @RequestBody @Valid PlateFormUpdate form) {
        Optional<Plate> opt = plateRepository.findById(id);
        if (opt.isPresent()) {
            Plate plate = form.update(id, plateRepository);
            return ResponseEntity.ok(new PlateDto(plate));
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Method to delete a plate from the database.
     * @param id
     * @return
     * @author: Luis Gregorio
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Plate> opt = plateRepository.findById(id);
        if(opt.isPresent()) {
            plateRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}