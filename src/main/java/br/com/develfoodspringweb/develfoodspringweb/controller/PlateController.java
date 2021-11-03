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

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/api/plate") //depois do merge com todos os projetos, realizar a troca do endpoint /api/restaurant/plate...
public class PlateController {

    private final PlateRepository plateRepository;
    private final RestaurantNameRepository restaurantNameRepository;

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

    @PostMapping
    public ResponseEntity<PlateDto> register(@RequestBody @Valid PlateForm form, UriComponentsBuilder uriBuilder) {
        Plate plate = form.convert(restaurantNameRepository);
        plateRepository.save(plate);
        URI uri = uriBuilder.path("/api/plate/{id}").buildAndExpand(plate.getId()).toUri();
        return ResponseEntity.created(uri).body(new PlateDto(plate));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<PlateDto> details(@PathVariable Long id) {
        Optional<Plate> plate = plateRepository.findById(id);
        if (plate.isPresent()) {
            return ResponseEntity.ok(new PlateDto(plate.get()));
        }

        return ResponseEntity.notFound().build();
    }

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